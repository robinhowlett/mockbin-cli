/**
 * 
 */
package org.springframework.social.mockbin.cli.providers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.BannerProvider;
import org.springframework.shell.support.util.VersionUtils;
import org.springframework.stereotype.Component;

/**
 * Customizes the CLI banner
 * 
 * @author robin
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MockbinCliBannerProvider implements BannerProvider {
	
	/*
	 * This will print ">_ Mockbin CLI" to the console
	 * 
	 * http://patorjk.com/software/taag/#p=display&f=Standard&t=%3E_%20Mockbin%20CLI%0A
	 */
	private static final String MOCKBIN_CLI_ASCII_ART = 
			" __        __  __            _    _     _          ____ _     ___ \r\n"
			+ " \\ \\      |  \\/  | ___   ___| | _| |__ (_)_ __    / ___| |   |_ _|\r\n"
			+ "  \\ \\     | |\\/| |/ _ \\ / __| |/ / '_ \\| | '_ \\  | |   | |    | | \r\n"
			+ "  / /     | |  | | (_) | (__|   <| |_) | | | | | | |___| |___ | | \r\n"
			+ " /_/____  |_|  |_|\\___/ \\___|_|\\_\\_.__/|_|_| |_|  \\____|_____|___|\r\n"
			+ "  |_____|";

	@Override
	public String getProviderName() {
		return "Mockbin CLI";
	}

	@Override
	public String getBanner() {
		return MOCKBIN_CLI_ASCII_ART;
	}

	@Override
	public String getVersion() {
		return VersionUtils.versionInfo();
	}

	@Override
	public String getWelcomeMessage() {
		return "Welcome to the " + getProviderName() + " powered by Spring Social Mockbin";
	}

}
