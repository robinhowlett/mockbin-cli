/**
 * 
 */
package org.springframework.social.mockbin.cli.test_support.dto.mocks;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.social.mockbin.cli.test_support.dto.mocks.MockHarHeaders.eightHarHeaders;

import org.springframework.http.HttpHeaders;

import com.sportslabs.amp.har.dto.entries.HarHeader;

/**
 * @author robin
 *
 */
public class MockHttpHeaders {

	public static HttpHeaders eightHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		
		for (HarHeader header : eightHarHeaders()) {
			headers.add(header.getName(), header.getValue());
		}
		
		return headers;
	}

	public static HttpHeaders eightHttpHeadersWithJsonContentType() {
		HttpHeaders headers = new HttpHeaders();
		
		for (HarHeader header : eightHarHeaders()) {
			if (header.getName().equals(CONTENT_TYPE)) {
				headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
			} else {
				headers.add(header.getName(), header.getValue());
			}
		}
		
		return headers;
	}

}
