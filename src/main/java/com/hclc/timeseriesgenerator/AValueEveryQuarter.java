package com.hclc.timeseriesgenerator;

import com.hclc.limitedrandoms.LimitedRandomsGenerator;
import com.hclc.limitedrandoms.LimitedRandomsInput;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.hclc.limitedrandoms.LimitType.SMOOTH_LIMIT;
import static java.math.RoundingMode.HALF_UP;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.util.stream.IntStream.range;

class AValueEveryQuarter {
    private static final long QUARTERS_IN_AN_HOUR = 4L;
    private static final long MINUTES_IN_A_QUARTER = 15L;
    private final ZonedDateTime sinceClosed;
    private final ZonedDateTime untilOpen;
    private final BigDecimal totalSum;
    private final List<BigDecimal> singleDayLimits;
    private TimeSeries timeSeries;
    private ZonedDateTime date;
    private BigDecimal sumOfADay;

    AValueEveryQuarter(ZonedDateTime sinceClosed, ZonedDateTime untilOpen, BigDecimal totalSum, List<BigDecimal> singleDayLimits) {
        this.sinceClosed = sinceClosed;
        this.untilOpen = untilOpen;
        this.totalSum = totalSum;
        this.singleDayLimits = singleDayLimits;
    }

    TimeSeries generate() {
        timeSeries = new TimeSeries();
        date = sinceClosed;
        int numberOfDays = (int) DAYS.between(sinceClosed, untilOpen);
        sumOfADay = totalSum.divide(BigDecimal.valueOf(numberOfDays), 4, HALF_UP);
        range(0, numberOfDays).forEach(i -> valuesInADay().forEach(this::addTimeSeriesEntry));
        return timeSeries;
    }

    private List<BigDecimal> valuesInADay() {
        long hours = HOURS.between(date, date.plusDays(1));
        long numberOfQuarters = hours * QUARTERS_IN_AN_HOUR;
        LimitedRandomsInput limitedRandomsInput = new LimitedRandomsInput(sumOfADay, (int) numberOfQuarters, 4, singleDayLimits, SMOOTH_LIMIT);
        return new LimitedRandomsGenerator(limitedRandomsInput).generate();
    }

    private void addTimeSeriesEntry(BigDecimal value) {
        timeSeries.add(new TimeSeriesEntry(date, value));
        date = date.plusMinutes(MINUTES_IN_A_QUARTER);
    }
}
