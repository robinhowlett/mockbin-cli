/**
 * 
 */
package org.springframework.social.mockbin.cli.test_support.dto.mocks;

import static org.springframework.http.HttpStatus.OK;

import com.sportslabs.amp.har.dto.alf.har.entries.AlfHarResponse;
import com.sportslabs.amp.har.dto.entries.HarContent;


/**
 * @author robin
 *
 */
public class MockHarResponses {
	
	public static final AlfHarResponse alfHarResponseWithEightHeadersAndHelloWorldHtmlBody() {
		return new AlfHarResponse(
				OK.value(), 
				OK.name(), 
				null, 
				MockHarHeaders.eightHarHeaders(), 
				helloWorldHtmlContent(), 
				323, 
				70993);
	}

	public static HarContent helloWorldHtmlContent() {
		return new HarContent(20, 0, "text/html; charset=UTF-8", "<h1>Hello World</h1>", null, null);
	}

}
