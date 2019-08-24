package com.hclc.timeseriesgenerator;

import java.math.BigDecimal;
import java.time.DayOfWeek;

import static java.math.BigDecimal.ZERO;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

class Statistics {
    private BigDecimal total = ZERO;
    private BigDecimal daysOfWeekTotal = ZERO;
    private BigDecimal weekendsTotal = ZERO;

    void include(TimeSeriesEntry entry) {
        BigDecimal value = entry.getValue();
        includeInTotal(value);
        includeInDayOfWeekAndWeekend(entry, value);
    }

    private void includeInTotal(BigDecimal value) {
        total = total.add(value);
    }

    private void includeInDayOfWeekAndWeekend(TimeSeriesEntry entry, BigDecimal value) {
        DayOfWeek dayOfWeek = entry.getDate().getDayOfWeek();
        if (dayOfWeek == SATURDAY || dayOfWeek == SUNDAY) {
            weekendsTotal = weekendsTotal.add(value);
        } else {
            daysOfWeekTotal = daysOfWeekTotal.add(value);
        }
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "total=" + total +
                ", daysOfWeekTotal=" + daysOfWeekTotal +
                ", weekendsTotal=" + weekendsTotal +
                '}';
    }
}
