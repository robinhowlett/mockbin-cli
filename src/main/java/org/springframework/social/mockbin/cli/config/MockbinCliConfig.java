/**
 * 
 */
package org.springframework.social.mockbin.cli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.shell.converters.CliPrinterTypeConverter;
import org.springframework.social.mockbin.Mockbin;
import org.springframework.social.mockbin.cli.converters.tables.AlfHarEntryTableCliPrinterTypeConverter;
import org.springframework.social.mockbin.impl.MockbinTemplate;
import org.springframework.social.shell.converters.json.JsonCliPrinterTypeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportslabs.amp.har.dto.alf.har.AlfHar;
import com.sportslabs.amp.har.dto.alf.har.entries.AlfHarEntry;

/**
 * Configures the {@link MockbinTemplate} and scans for Shell command classes
 * 
 * <p>Two {@link CliPrinterTypeConverter}s are also registered for printing HTTP Archive objects in
 * either JSON or ASCII table formats e.g. 
 * 
 * <p>"access-log --id 123456 --p json" will print the {@link AlfHarEntry} access log as JSON, while
 * 
 * <p>"access-log --id 123456 --p table" will print the {@link AlfHarEntry} access log as an ASCII table
 * 
 * <p>The "xyzOutputConverter" naming convention must be followed for these types of beans
 * 
 * @author robin
 */
@Configuration
@ComponentScan
public class MockbinCliConfig {

	@Bean
	public Mockbin mockbin() {
		return new MockbinTemplate();
	}
	
	/**
	 * Naming convention corresponds to printer parameter value "json"
	 * 
	 * Uses Jackson {@link ObjectMapper} to print the JSON representation of an Access Log to the console
	 */
	@Bean
	@DependsOn("mockbin")
	public JsonCliPrinterTypeConverter<AlfHar> jsonOutputConverter() {
		return new JsonCliPrinterTypeConverter<AlfHar>(mockbin().getObjectMapper());
	}
	
	/**
	 * Naming convention corresponds to printer parameter value "json"
	 * 
	 * Uses "java-ascii-table" project to print the Access Log Entries within an ASCII table
	 */
	@Bean
	@DependsOn("mockbin")
	public AlfHarEntryTableCliPrinterTypeConverter tableOutputConverter() {
		return new AlfHarEntryTableCliPrinterTypeConverter();
	}
	
}
