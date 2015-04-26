/**
 * 
 */
package org.springframework.social.mockbin.cli.test_support.dto.mocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sportslabs.amp.har.dto.entries.HarCookie;

/**
 * @author robin
 *
 */
public class MockHarCookies {
	
	public static final List<HarCookie> harCookieArrayWithTwoCookies() {
		List<HarCookie> cookies = new ArrayList<HarCookie>();
		cookies.add(myCookie());
		cookies.add(foo());
		
		return cookies;
	}
	
	public static final HarCookie myCookie() {
		return new HarCookie("my-cookie", "ALL YOUR BASE ARE BELONG TO US", null, null, null, null, null, null);
	}
	
	public static final HarCookie foo() {
		return new HarCookie("foo", "bar", null, null, null, null, null, null);
	}
	
	public static final Map<String, String> cookiesAsStringMap() {
		Map<String, String> cookies = new HashMap<String, String>();
		
		for (HarCookie cookie : harCookieArrayWithTwoCookies()) {
			cookies.put(cookie.getName(), cookie.getValue());
		}
		
		return cookies;
	}

}
