/**
 * 
 */
package org.springframework.social.mockbin.cli.test_support.dto.mocks;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.social.mockbin.cli.test_support.dto.mocks.MockHarHeaders.eightHarHeaders;

import java.net.MalformedURLException;
import java.net.URL;

import com.sportslabs.amp.har.dto.alf.har.entries.AlfHarEntry;
import com.sportslabs.amp.har.dto.alf.har.entries.AlfHarRequest;

/**
 * @author robin
 *
 */
public class MockHarRequests {
	
	public static final AlfHarRequest getBinRequestWithEightHeaders() throws MalformedURLException {
		return new AlfHarRequest(
				GET.name(), 
				new URL("http://mockbin.com/bin/3c149e20-bc9c-4c68-8614-048e6023a108"), 
				null, 
				null, 
				eightHarHeaders(), 
				null, 
				500, 
				750, 
				null);
	}
	
	public static final AlfHarEntry getBinEntryWithRequestWithEightHeaders() throws MalformedURLException {
		return new AlfHarEntry(null, null, getBinRequestWithEightHeaders(), null, null, null, null);
	}

}
