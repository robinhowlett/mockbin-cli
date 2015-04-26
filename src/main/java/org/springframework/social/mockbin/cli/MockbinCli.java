/**
 * 
 */
package org.springframework.social.mockbin.cli;

import java.io.IOException;

import org.springframework.shell.Bootstrap;
import org.springframework.social.mockbin.Mockbin;
import org.springframework.social.shell.BaseApiCli;

/**
 * Command-line interface for {@link Mockbin}
 * 
 * @author robin
 */
public class MockbinCli extends BaseApiCli {

	/* (non-Javadoc)
	 * @see org.springframework.social.cli.BaseApiCli#basePackages()
	 */
	@Override
	public String[] basePackages() {
		return new String[] { getClass().getPackage().getName() };
	}
	
	public static void main(String[] args) throws IOException {
		Bootstrap.main(args, new MockbinCli().basePackages());
	}

}
