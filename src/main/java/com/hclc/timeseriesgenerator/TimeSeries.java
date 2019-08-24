package com.hclc.timeseriesgenerator;

import java.util.LinkedList;
import java.util.List;

class TimeSeries {
    private final List<TimeSeriesEntry> entries = new LinkedList<>();
    private final Statistics statistics = new Statistics();

    void add(TimeSeriesEntry entry) {
        entries.add(entry);
        statistics.include(entry);
    }

    void print() {
        for (TimeSeriesEntry entry : entries) {
            System.out.println(entry.toCsv());
        }
        System.out.println("statistics:" + statistics);
    }
}
