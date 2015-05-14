/**
 * 
 */
package org.springframework.social.mockbin.cli.commands;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.CommandResult;
import org.springframework.shell.core.JLineShellComponent;
import org.springframework.social.mockbin.cli.converters.tables.AlfHarEntryTableCliPrinterTypeConverter;
import org.springframework.social.mockbin.cli.test_support.config.MockbinCliTestConfig;
import org.springframework.social.mockbin.cli.test_support.dto.mocks.MockHar;
import org.springframework.social.shell.converters.json.JsonCliPrinterTypeConverter;

import com.sportslabs.amp.har.dto.alf.har.AlfHar;

/**
 * Tests executing {@link BinAccessLogCommands} that return mock data
 * 
 * @author robin
 */
public class BinAccessLogCommandsTest {

	protected static Bootstrap bootstrap;
	
	private static JLineShellComponent shell;
	
	@BeforeClass
	public static void startUp() throws InterruptedException {
		bootstrap = new Bootstrap(null, Bootstrap.CONTEXT_PATH, MockbinCliTestConfig.class.getPackage().getName());
		
		shell = bootstrap.getJLineShellComponent();
	}
	
	@AfterClass
	public static void shutdown() {
		shell.stop();
	}
	
	public static JLineShellComponent getShell() {
		return shell;
	}

	/**
	 * Test that execution of the {@link BinAccessLogCommands#accessLog(String, org.springframework.shell.converters.CliPrinterTypeConverter)}
	 * command returns an ASCII table of the HAR Log Entries
	 * 
	 * @throws MalformedURLException 
	 */
	@Test
	public void accessLogCommand_WithBinIdAndDefaultOutput_ReturnsAsciiTableOfMockAccessLogEntries() throws MalformedURLException {
		CommandResult cr = getShell().executeCommand("access-log --id 800a818b-5fb6-40d4-a342-75a1fb8599db");

		String result = cr.getResult().toString();
		
		AlfHarEntryTableCliPrinterTypeConverter tableConverter = 
				bootstrap.getApplicationContext().getBean(AlfHarEntryTableCliPrinterTypeConverter.class);
		
		String table = tableConverter.convert(MockHar.alfHarWithLogContainingSingleEntry());
		
		assertThat(result, equalTo(table));
	}
	
	/**
	 * Test that execution of the {@link BinAccessLogCommands#accessLog(String, org.springframework.shell.converters.CliPrinterTypeConverter)}
	 * command returns the JSON representation of the HTTP Archive object
	 * 
	 * @throws MalformedURLException 
	 */
	@Test
	public void accessLogCommand_WithBinIdAndJsonOutput_ReturnsJsonOfMockAccessLogEntries() throws MalformedURLException {
		CommandResult cr = getShell().executeCommand("access-log --id 800a818b-5fb6-40d4-a342-75a1fb8599db --p json");

		String result = cr.getResult().toString();
		
		@SuppressWarnings("unchecked")
		JsonCliPrinterTypeConverter<AlfHar> jsonConverter = 
				bootstrap.getApplicationContext().getBean(JsonCliPrinterTypeConverter.class);
		
		String json = jsonConverter.convert(MockHar.alfHarWithLogContainingSingleEntry());
		
		assertThat(result, equalTo(json));
	}
	
	/**
	 * Test that execution of the {@link BinAccessLogCommands#accessLog(String, org.springframework.shell.converters.CliPrinterTypeConverter)}
	 * command returns the {@link #toString()} representation of the HTTP Archive object
	 * 
	 * @throws MalformedURLException 
	 */
	@Test
	public void accessLogCommand_WithBinIdAndUnspecifiedOutput_ReturnsToStringOfMockAccessLogEntries() throws MalformedURLException {
		CommandResult cr = getShell().executeCommand("access-log --id 800a818b-5fb6-40d4-a342-75a1fb8599db --p");

		String result = cr.getResult().toString();
		
		assertThat(result, equalTo(MockHar.alfHarWithLogContainingSingleEntry().toString()));
	}
	
}
