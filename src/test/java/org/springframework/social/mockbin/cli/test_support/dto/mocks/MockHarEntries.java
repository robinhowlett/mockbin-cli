/**
 * 
 */
package org.springframework.social.mockbin.cli.test_support.dto.mocks;

import static org.springframework.social.mockbin.cli.test_support.dto.mocks.MockHar.JULY_1_2014_MIDNIGHT_UTC;
import static org.springframework.social.mockbin.cli.test_support.dto.mocks.MockHarRequests.getBinRequestWithEightHeaders;
import static org.springframework.social.mockbin.cli.test_support.dto.mocks.MockHarResponses.alfHarResponseWithEightHeadersAndHelloWorldHtmlBody;

import java.net.MalformedURLException;

import com.sportslabs.amp.har.dto.alf.har.entries.AlfHarEntry;
import com.sportslabs.amp.har.dto.entries.HarEntryTimings;

/**
 * @author robin
 *
 */
public class MockHarEntries {

	public static final AlfHarEntry alfHarEntryWithRequestAndResponse() throws MalformedURLException {
		HarEntryTimings timings = new HarEntryTimings(null, null, null, 0, 0, 1000, -1, null);
		
		return new AlfHarEntry(
				JULY_1_2014_MIDNIGHT_UTC, 
				1000, 
				getBinRequestWithEightHeaders(), 
				alfHarResponseWithEightHeadersAndHelloWorldHtmlBody(), 
				timings, 
				null, 
				"40.140.33.170");
	}

}
