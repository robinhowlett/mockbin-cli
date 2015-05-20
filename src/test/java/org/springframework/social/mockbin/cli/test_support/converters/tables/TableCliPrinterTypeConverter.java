package org.springframework.social.mockbin.cli.test_support.converters.tables;

import com.google.common.base.Splitter;
import com.sportslabs.amp.har.dto.alf.har.AlfHar;
import org.springframework.shell.converters.CliPrinterTypeConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robin on 5/19/15.
 */
public class TableCliPrinterTypeConverter implements CliPrinterTypeConverter<Object> {

    List<String> exps;

    public TableCliPrinterTypeConverter(String expressions) {
        exps = splitExpressions(expressions);
    }

    public static List<String> splitExpressions(String expressions) {
        List<String> exps = new ArrayList<>();

        Iterable<String> exs = Splitter.on(",").split(expressions);

        for (String exp : exs) {
            Iterable<String> split = Splitter.on("|").split(exp);

            int index = 0;
            for (String components : split) {

            }
        }

        // split expressions
        // for each expression
        // split on pipe
        // 1 = object expression
        // 2 = label
        // 3 = alignment
        // 4 = color?
        // store them in a list
        //

        return exps;
    }

    @Override
    public String convert(Object o) {
        return null;
    }
}
