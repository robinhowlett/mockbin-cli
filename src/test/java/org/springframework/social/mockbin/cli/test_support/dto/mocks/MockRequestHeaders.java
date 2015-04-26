/**
 * 
 */
package org.springframework.social.mockbin.cli.test_support.dto.mocks;

import static org.springframework.social.mockbin.cli.test_support.dto.mocks.MockHarHeaders.eightHarHeaders;

import org.springframework.social.mockbin.dto.RequestHeaders;

/**
 * @author robin
 *
 */
public class MockRequestHeaders {
	
	public static final RequestHeaders eightHarHeadersAndHeaderSize500() {
		return new RequestHeaders(eightHarHeaders(), 500);
	}

}
