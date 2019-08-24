package com.hclc.timeseriesgenerator;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.hclc.timeseriesgenerator.DateTimeFormat.ISO_8601_OFFSET_DATE_TIME_LESS_PRECISION;
import static java.time.format.DateTimeFormatter.ofPattern;

class TimeSeriesEntry {
    private static final DateTimeFormatter dateTimeFormatter = ofPattern(ISO_8601_OFFSET_DATE_TIME_LESS_PRECISION);
    private final ZonedDateTime date;
    private final BigDecimal value;

    TimeSeriesEntry(ZonedDateTime date, BigDecimal value) {
        this.date = date;
        this.value = value;
    }

    ZonedDateTime getDate() {
        return date;
    }

    BigDecimal getValue() {
        return value;
    }

    String toCsv() {
        return dateTimeFormatter.format(date) + ";" + value;
    }
}
