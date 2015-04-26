/**
 * 
 */
package org.springframework.social.mockbin.cli.test_support.config;

import static org.springframework.social.mockbin.cli.test_support.dto.mocks.MockHar.alfHarWithLogContainingSingleEntry;

import java.net.MalformedURLException;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.social.mockbin.Mockbin;
import org.springframework.social.mockbin.cli.commands.BinAccessLogCommands;
import org.springframework.social.mockbin.cli.converters.tables.AlfHarEntryTableCliPrinterTypeConverter;
import org.springframework.social.mockbin.impl.MockbinTemplate;
import org.springframework.social.mockbin.operations.resources.BinOperations;
import org.springframework.social.shell.converters.json.JsonCliPrinterTypeConverter;

import com.sportslabs.amp.har.dto.alf.har.AlfHar;

/**
 * Configures a spy {@link MockbinTemplate} to return a mock Bin Access Log
 * 
 * @author robin
 */
@Configuration
@ComponentScan(basePackageClasses={ BinAccessLogCommands.class })
public class MockbinCliTestConfig {

	@Bean
	public Mockbin mockbin() throws MalformedURLException {
		MockbinTemplate mockbin = Mockito.spy(new MockbinTemplate());
		
		BinOperations spy = Mockito.spy(mockbin.binOperations());
		
		Mockito.doReturn(spy).when(mockbin).binOperations();
		Mockito.doReturn(alfHarWithLogContainingSingleEntry()).when(spy).accessLog("800a818b-5fb6-40d4-a342-75a1fb8599db");
		
		return mockbin;
	}
	
	@Bean
	@DependsOn("mockbin")
	public JsonCliPrinterTypeConverter<AlfHar> jsonOutputConverter() throws MalformedURLException {
		return new JsonCliPrinterTypeConverter<AlfHar>(mockbin().getObjectMapper());
	}
	
	@Bean
	@DependsOn("mockbin")
	public AlfHarEntryTableCliPrinterTypeConverter tableOutputConverter() {
		return new AlfHarEntryTableCliPrinterTypeConverter();
	}
	
}
