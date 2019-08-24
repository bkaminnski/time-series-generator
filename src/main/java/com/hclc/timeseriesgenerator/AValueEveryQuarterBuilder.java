package com.hclc.timeseriesgenerator;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

class AValueEveryQuarterBuilder {
    private ZonedDateTime sinceClosed;
    private ZonedDateTime untilOpen;
    private BigDecimal totalSum;
    private List<BigDecimal> limits;

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

    AValueEveryQuarterBuilder withLimits(List<BigDecimal> limits) {
        this.limits = limits;
        return this;
    }

    AValueEveryQuarter build() {
        return new AValueEveryQuarter(sinceClosed, untilOpen, totalSum, limits);
    }
}