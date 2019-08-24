package com.hclc.timeseriesgenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.hclc.timeseriesgenerator.AValueEveryQuarterBuilder.aValueEveryQuarter;
import static com.hclc.timeseriesgenerator.DateTimeFormat.ISO_8601_DATE;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

public class TimeSeriesGenerator {
    private static final DateTimeFormatter dateFormatter = ofPattern(ISO_8601_DATE);

    public static void main(String... args) {
        if (args.length != 4) {
            printHelp();
            return;
        }

        ZonedDateTime sinceClosed = LocalDate.parse(args[0], dateFormatter).atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime untilOpen = LocalDate.parse(args[1], dateFormatter).atStartOfDay(ZoneId.systemDefault());
        BigDecimal totalSum = new BigDecimal(args[2]);
        List<BigDecimal> singleDayLimits = of(args[3].split(";")).map(BigDecimal::new).collect(toList());

        aValueEveryQuarter()
                .withSinceClosed(sinceClosed)
                .withUntilOpen(untilOpen)
                .withTotalSum(totalSum)
                .withLimits(singleDayLimits)
                .build()
                .generate()
                .print();
    }

    private static void printHelp() {
        System.out.print("<yyyy-MM-dd since closed> <yyyy-MM-dd until open> <total sum> <semicolon separated limits for a single day>");
    }
}
