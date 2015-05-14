/**
 *
 */
package org.springframework.social.mockbin.cli;

import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.ExitShellRequest;
import org.springframework.shell.support.logging.HandlerUtils;
import org.springframework.social.mockbin.Mockbin;
import org.springframework.social.shell.BaseApiCli;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.logging.Logger;

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
        return new String[]{getClass().getPackage().getName()};
    }

    public static void main(String[] args) throws IOException {
        sw.start();

        ExitShellRequest exitShellRequest;
        try {
            Bootstrap bootstrap = new Bootstrap(args, Bootstrap.CONTEXT_PATH, new MockbinCli().basePackages());
            exitShellRequest = bootstrap.run();
        } catch (RuntimeException var6) {
            throw var6;
        } finally {
            HandlerUtils.flushAllHandlers(Logger.getLogger(""));
        }

        System.exit(exitShellRequest.getExitCode());
    }

}
