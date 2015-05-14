/**
 * 
 */
package org.springframework.social.mockbin.cli.commands;

import java.io.File;
import java.io.IOException;
import java.net.BindException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.shell.converters.CliPrinterTypeConverter;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.shell.core.annotation.CliPrinter;
import org.springframework.social.mockbin.Mockbin;
import org.springframework.social.operations.resources.BaseApiResourceOperations;
import org.springframework.social.shell.commands.BaseApiResourceCommands;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sportslabs.amp.har.dto.alf.har.AlfHar;
import com.sportslabs.amp.har.dto.alf.har.entries.AlfHarEntry;

/**
 * @author robin
 *
 */
@Component
public class BinCreationCommands implements BaseApiResourceCommands, CommandMarker {
	
	@Autowired
	private Mockbin mockbin;
	
	/**
	 * Command to load a HAR- an ALF-compatible JSON file and save each entry as a new 
	 * bin.
	 * 
	 * @param path Path to HAR file
	 * @param printer (Default: ASCII table) CLI Printer
	 * @return HAR file with "_binId" property of each entry populated with the Bin created
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@CliCommand(value = { "har-to-bins" })
	public AlfHar harFileToBins(
			@CliOption(key="path", mandatory=true) String path,
			@CliPrinter(defaultValue="table") CliPrinterTypeConverter<AlfHar> printer) throws JsonParseException, JsonMappingException, IOException {
		FileSystemResource resource = new FileSystemResource(path);
		
		File file = resource.getFile();
		
		AlfHar har = mockbin.getObjectMapper().readValue(file, AlfHar.class);

		if (har != null && har.getLog() != null) {
			for (AlfHarEntry entry : har.getLog().getEntries()) {
				String newBinId = mockbin.binOperations().create(entry.getResponse());
				entry.set_binId(newBinId);
			}
		}
		
		return har;
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.shell.commands.BaseApiResourceCommands#resourceOperations()
	 */
	@Override
	public BaseApiResourceOperations<?> resourceOperations() {
		return mockbin.binOperations();
	}

}
