/**
 * 
 */
package org.springframework.social.mockbin.cli.providers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.PromptProvider;
import org.springframework.stereotype.Component;

/**
 * Customizes the CLI prompt
 * 
 * @author robin
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MockbinPromptProvider implements PromptProvider {

	/* (non-Javadoc)
	 * @see org.springframework.shell.plugin.NamedProvider#getProviderName()
	 */
	@Override
	public String getProviderName() {
		return "Mockbin CLI Prompt Provider";
	}

	/* (non-Javadoc)
	 * @see org.springframework.shell.plugin.PromptProvider#getPrompt()
	 */
	@Override
	public String getPrompt() {
		return "mockbin-cli>";
	}

}
