package com.hclc.timeseriesgenerator;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

class AValueEveryQuarterBuilder {
    private ZonedDateTime sinceClosed;
    private ZonedDateTime untilOpen;
    private BigDecimal totalSum;
    private List<BigDecimal> wholePeriodLimits;
    private List<BigDecimal> singleDayLimits;

    static AValueEveryQuarterBuilder aValueEveryQuarter() {
        return new AValueEveryQuarterBuilder();
    }

    AValueEveryQuarterBuilder withSinceClosed(ZonedDateTime sinceClosed) {
        this.sinceClosed = sinceClosed;
        return this;
    }

    AValueEveryQuarterBuilder withUntilOpen(ZonedDateTime untilOpen) {
        this.untilOpen = untilOpen;
        return this;
    }

    AValueEveryQuarterBuilder withTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
        return this;
    }

    AValueEveryQuarterBuilder withWholePeriodLimits(List<BigDecimal> wholePeriodLimits) {
        this.wholePeriodLimits = wholePeriodLimits;
        return this;
    }

    AValueEveryQuarterBuilder withSingleDayLimits(List<BigDecimal> singleDayLimits) {
        this.singleDayLimits = singleDayLimits;
        return this;
    }

    AValueEveryQuarter build() {
        return new AValueEveryQuarter(sinceClosed, untilOpen, totalSum, wholePeriodLimits, singleDayLimits);
    }
}