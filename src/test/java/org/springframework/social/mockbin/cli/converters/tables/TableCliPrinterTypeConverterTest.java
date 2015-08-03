package org.springframework.social.mockbin.cli.converters.tables;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.social.shell.tables.TableCliPrinterTypeConverter;

import static org.springframework.social.mockbin.cli.test_support.dto.mocks.MockHar.alfHarWithLogContainingSingleEntry;

/**
 * Created by robin on 5/19/15.
 */
public class TableCliPrinterTypeConverterTest {

    @Test
    public void convert_WithHarLogOfOneHarEntry_PrintsAsciiTableSingleRowWithIndexMethodAndClientIP() throws Exception {
        TableCliPrinterTypeConverter converter = new TableCliPrinterTypeConverter();
        converter.setParameters("{#_, Method<, Size (KB)>, IP<}|#root.log.entries.![{request.method, request.bodySize.toString(), clientIPAddress ?: ''}]");

        String table = converter.convert(alfHarWithLogContainingSingleEntry());

        String expectedTable =
                "+---+--------+----------+---------------+\n" +
                "| # | Method | Size(KB) |       IP      |\n" +
                "+---+--------+----------+---------------+\n" +
                "| 1 | GET    |      750 | 40.140.33.170 |\n" +
                "+---+--------+----------+---------------+\n";

        Assert.assertThat(table, Matchers.equalTo(expectedTable));
    }
}
