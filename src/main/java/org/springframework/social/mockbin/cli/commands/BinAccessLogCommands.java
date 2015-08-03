/**
 * 
 */
package org.springframework.social.mockbin.cli.commands;

import static java.lang.Integer.parseInt;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;
import static org.springframework.util.StringUtils.hasText;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.converters.CliPrinterTypeConverter;
import org.springframework.shell.core.AbstractStepExecutionProcessor;
import org.springframework.shell.core.CliPrinterResult;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.shell.core.annotation.CliPrinter;
import org.springframework.shell.core.annotation.CliStepIndicator;
import org.springframework.shell.event.ParseResult;
import org.springframework.social.mockbin.Mockbin;
import org.springframework.social.mockbin.operations.resources.BinOperations;
import org.springframework.social.shell.commands.BaseApiResourceCommands;
import org.springframework.stereotype.Component;

import com.sportslabs.amp.har.dto.alf.har.AlfHar;
import com.sportslabs.amp.har.dto.alf.har.entries.AlfHarEntry;

/**
 * CLI commands for querying and examining a Mockbin Bin Access Log
 * 
 * <p>The "access-log" prints an ASCII table of a Bin's history.
 * 
 * <p>This {@link #accessLog(String, CliPrinterTypeConverter)} command supports multi-step execution (due to {@link CliStepIndicator} 
 * annotation being present and the class extending {@link AbstractStepExecutionProcessor}). 
 * 
 * <p>After the Access Log ASCII table is printed, a Bin Log Entry index may be selected by the user and the Entry's JSON 
 * representation printed, until the command is instructed to no longer loop/execute.
 *
 * @author robin
 */
@Component
public class BinAccessLogCommands extends AbstractStepExecutionProcessor implements BaseApiResourceCommands {

	@Autowired
	private Mockbin mockbin;
	
	protected boolean doPrintTable = true;
	protected boolean hasMoreSteps;
	
	/**
	 * Command to retrieve a specified Bin's access log and for its entries to be printed 
	 * to an ASCII table
	 * 
	 * @param binId The Bin ID
	 * @param printer (Optional, parameter "--p", default value is "table") The formatter/type converter to apply to 
	 * the the command output. Default is "table" for ASCII table; alternative is "json", or
	 * unspecified value prints {@link AlfHar#toString()} output
	 * @return The Bin Access {@link AlfHar} containing the log of entries 
	 */
	@CliCommand(value = { "access-log" })
	@CliStepIndicator
	public AlfHar accessLog(
			@CliOption(key="id", mandatory=true) String binId,
			@CliPrinter(defaultValue="table2") CliPrinterTypeConverter<AlfHar> printer) {
		return mockbin.binOperations().accessLog(binId);
	}
	
	@Override
	public BinOperations resourceOperations() {
		return mockbin.binOperations();
	}
	
	@Override
	public ParseResult beforeInvocation(ParseResult invocationContext) {
		hasMoreSteps = true;
		
		return super.beforeInvocation(invocationContext);
	}

	/**
	 * Print the {@link AlfHar} to the console using the {@link CliPrinterTypeConverter} (if set)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void handleStepExecutionResult(ParseResult invocationContext, Object stepResult) {
		if (stepResult != null && AlfHar.class.isAssignableFrom(stepResult.getClass()) && doPrintTable) {
			AlfHar har = (AlfHar) stepResult;
			CliPrinterTypeConverter<AlfHar> printer = 
					(CliPrinterTypeConverter<AlfHar>) invocationContext.getPrinter();

			// Spring Shell won't print the same message (the table) twice in a row, so the fine log forces a delta
			shell.getLogger().fine("");
			shell.getLogger().info(new CliPrinterResult<AlfHar>(har, printer).toString());
		}
	}

	@Override
	protected boolean hasMoreSteps(Object stepResult) {
		return hasMoreSteps;
	}

	@Override
	protected Object configureStep(Object stepResult) {
		if (shell.getReader() != null) {
			try {
				/*
				 * Read input from the user to either print an Access Log Entry (in JSON), 
				 * print the Access Log History again (in an ASCII table), or
				 * quit executing the command
				 */			
				String input = shell.getReader().readLine("\nEnter index # of HTTP Archive Entry to view, 't' to print table again or 'q' to quit: ");
				
				shell.getReader().flush();
				
				if (hasText(input)) {
					Pattern intPattern = compile("^([0-9]*)$", CASE_INSENSITIVE);
				
					Matcher intMatcher = intPattern.matcher(input);
					
					// If a number was entered, check if it is a valid Access Log Entry index
					if (intMatcher.matches()) {
						Integer index = parseInt(intMatcher.group(1));
						
						if (stepResult != null && AlfHar.class.isAssignableFrom(stepResult.getClass())) {
							AlfHar har = (AlfHar) stepResult;
							
							if (index > 0 && index < har.getLog().getEntries().size()) {
								return index;
							}
						}
					} else if (input.equalsIgnoreCase("t")) {	// Print the Access Log ASCII table
						doPrintTable = true;
						return input;
					}
				}
			} catch (IOException e) {
				shell.getLogger().log(Level.SEVERE, e.getMessage());
			} catch (NumberFormatException e) {
				shell.getLogger().log(Level.SEVERE, e.getMessage());
			}
		}
		
		return null;	// Indicate that the command should stop executing
	}

	@Override
	protected Object executeStep(Object stepResult, Object stepConfig) {
		AlfHar har = null;
		if (stepConfig != null && AlfHar.class.isAssignableFrom(stepResult.getClass())) {
			har = (AlfHar) stepResult;
			
			// If an number was entered, print the Access Log Entry that represents that index
			if (Integer.class.isAssignableFrom(stepConfig.getClass())) {
				doPrintTable = false;
				Integer index = (Integer) stepConfig;
				
				AlfHarEntry entry = har.getLog().getEntries().get(index - 1);
				
				try {
					// Print the Access Log Entry (in JSON)
					shell.getLogger().info(mockbin.getObjectMapper().writeValueAsString(entry));
					
					shell.getReader().flush();
				} catch (UnsupportedEncodingException e) {
					shell.getLogger().log(Level.SEVERE, e.getMessage());
				} catch (IOException e) {
					shell.getLogger().log(Level.SEVERE, e.getMessage());
				}
			}		
		} else {
			hasMoreSteps = false;	// Indicate that the command should stop executing
		}
		
		return har;
	}

}
