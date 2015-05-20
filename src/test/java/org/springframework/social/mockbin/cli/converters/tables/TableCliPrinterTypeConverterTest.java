package org.springframework.social.mockbin.cli.converters.tables;

import org.junit.Test;
import org.springframework.social.mockbin.cli.test_support.converters.tables.TableCliPrinterTypeConverter;
import org.springframework.social.mockbin.cli.test_support.dto.mocks.MockHar;

/**
 * Created by robin on 5/19/15.
 */
public class TableCliPrinterTypeConverterTest {

    @Test
    public void convert_WithBasicObject_ParsesSpelCorrectly() throws Exception {
        TableCliPrinterTypeConverter converter = new TableCliPrinterTypeConverter();
        String table = converter.convert(MockHar.alfHarWithLogContainingSingleEntry());

        System.out.println(table);
    }
}
