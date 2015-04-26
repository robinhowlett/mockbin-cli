/**
 * 
 */
package org.springframework.social.mockbin.cli.converters.tables;

import static com.bethecoder.ascii_table.spec.IASCIITable.ALIGN_LEFT;
import static java.util.Arrays.asList;
import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.shell.converters.CliPrinterTypeConverter;

import com.bethecoder.ascii_table.ASCIITable;
import com.bethecoder.ascii_table.ASCIITableHeader;
import com.sportslabs.amp.har.dto.alf.har.AlfHar;
import com.sportslabs.amp.har.dto.alf.har.entries.AlfHarEntry;

/**
 * Convert an {@link AlfHar} to an ASCII table of its Log's {@link AlfHarEntry} objects
 *
 * @author robin
 */
public class AlfHarEntryTableCliPrinterTypeConverter implements CliPrinterTypeConverter<AlfHar> {

	@Override
	public String convert(AlfHar har) {
		notNull(har, "HTTP Archive object must not be null");
		notNull(har.getLog(), "HTTP Archive log must not be null");
		
		List<ASCIITableHeader> tableHeaders = Arrays.asList(
				new ASCIITableHeader("#"),
				new ASCIITableHeader("Method", ALIGN_LEFT), 
				new ASCIITableHeader("Path", ALIGN_LEFT),
				new ASCIITableHeader("Size (KB)"),
				new ASCIITableHeader("Date", ALIGN_LEFT),
				new ASCIITableHeader("IP", ALIGN_LEFT));
		
		ASCIITableHeader[] headers = tableHeaders.toArray(new ASCIITableHeader[tableHeaders.size()]);
		String[][] cells = null;
		
		Integer rowNumber = 1;
		List<String[]> rows = new ArrayList<String[]>();
		for (AlfHarEntry entry : har.getLog().getEntries()) {
			List<String> row = asList(
				rowNumber.toString(),
				entry.getRequest().getMethod(),
				entry.getRequest().getUrl().toString(),
				entry.getRequest().getBodySize().toString(),
				new PrettyTime().format(entry.getStartedDateTime().toDate()),
				entry.getClientIPAddress()
			);
			
			rows.add(row.toArray(new String[row.size()]));
			rowNumber++;
		}
		
		if (!rows.isEmpty()) {
			cells = rows.toArray(new String[rows.size()][]);
		}
		
		return ASCIITable.getInstance().getTable(
				headers, 
				cells);
	}

}
