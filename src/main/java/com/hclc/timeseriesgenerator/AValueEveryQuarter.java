package com.hclc.timeseriesgenerator;

import com.hclc.limitedrandoms.LimitedRandomsGenerator;
import com.hclc.limitedrandoms.LimitedRandomsInput;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.hclc.limitedrandoms.LimitType.SMOOTH_LIMIT;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.util.stream.IntStream.range;

class AValueEveryQuarter {
    private static final long QUARTERS_IN_AN_HOUR = 4L;
    private static final long MINUTES_IN_A_QUARTER = 15L;
    private final ZonedDateTime sinceClosed;
    private final ZonedDateTime untilOpen;
    private final BigDecimal totalSum;
    private final List<BigDecimal> wholePeriodLimits;
    private final List<BigDecimal> singleDayLimits;
    private TimeSeries timeSeries;
    private ZonedDateTime date;
    private List<BigDecimal> sumOfADay;

    AValueEveryQuarter(ZonedDateTime sinceClosed, ZonedDateTime untilOpen, BigDecimal totalSum, List<BigDecimal> wholePeriodLimits, List<BigDecimal> singleDayLimits) {
        this.sinceClosed = sinceClosed;
        this.untilOpen = untilOpen;
        this.totalSum = totalSum;
        this.wholePeriodLimits = wholePeriodLimits;
        this.singleDayLimits = singleDayLimits;
    }

    TimeSeries generate() {
        timeSeries = new TimeSeries();
        int numberOfDays = (int) DAYS.between(sinceClosed, untilOpen);
        prepareSumForEachDay(numberOfDays);
        date = sinceClosed;
        range(0, numberOfDays).forEach(i -> valuesInADay(i).forEach(this::addTimeSeriesEntry));
        return timeSeries;
    }

    private void prepareSumForEachDay(int numberOfDays) {
        LimitedRandomsInput limitedRandomsInput = new LimitedRandomsInput(totalSum, numberOfDays, 4, wholePeriodLimits, SMOOTH_LIMIT);
        sumOfADay = new LimitedRandomsGenerator(limitedRandomsInput).generate();
    }

    private List<BigDecimal> valuesInADay(int day) {
        long hours = HOURS.between(date, date.plusDays(1));
        long numberOfQuarters = hours * QUARTERS_IN_AN_HOUR;
        LimitedRandomsInput limitedRandomsInput = new LimitedRandomsInput(sumOfADay.get(day), (int) numberOfQuarters, 4, singleDayLimits, SMOOTH_LIMIT);
        return new LimitedRandomsGenerator(limitedRandomsInput).generate();
    }

    private void addTimeSeriesEntry(BigDecimal value) {
        timeSeries.add(new TimeSeriesEntry(date, value));
        date = date.plusMinutes(MINUTES_IN_A_QUARTER);
    }
}
