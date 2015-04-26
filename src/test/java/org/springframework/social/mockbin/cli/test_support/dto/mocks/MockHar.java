/**
 * 
 */
package org.springframework.social.mockbin.cli.test_support.dto.mocks;

import static org.springframework.social.mockbin.cli.test_support.dto.mocks.MockHarEntries.alfHarEntryWithRequestAndResponse;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.sportslabs.amp.har.dto.alf.har.AlfHar;
import com.sportslabs.amp.har.dto.alf.har.entries.AlfHarEntry;
import com.sportslabs.amp.har.dto.alf.har.log.AlfHarLog;
import com.sportslabs.amp.har.dto.creator.HarCreator;

/**
 * @author robin
 *
 */
public class MockHar {
	
	public static final DateTime JULY_1_2014_MIDNIGHT_UTC = new DateTime(2014, 7, 1, 0, 0, DateTimeZone.UTC);

	public static final AlfHar alfHarWithLogContainingSingleEntry() throws MalformedURLException {
		HarCreator creator = new HarCreator("mockbin.com", "1.5.0", null);
		
		List<AlfHarEntry> entries = new ArrayList<AlfHarEntry>();
		entries.add(alfHarEntryWithRequestAndResponse());
		
		AlfHarLog log = new AlfHarLog("1.2", creator, entries);
		
		return new AlfHar(log);
	}
}
