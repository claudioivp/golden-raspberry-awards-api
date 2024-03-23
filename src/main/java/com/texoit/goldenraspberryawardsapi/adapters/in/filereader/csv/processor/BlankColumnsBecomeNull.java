package com.texoit.goldenraspberryawardsapi.adapters.in.filereader.csv.processor;

import com.opencsv.processor.RowProcessor;

public class BlankColumnsBecomeNull implements RowProcessor {

    @Override
    public String processColumnItem(String column) {
        if (column == null || !column.isEmpty()) {
            return column;
        } else {
            return null;
        }
    }

    @Override
    public void processRow(String[] row) {
        for (int i = 0; i < row.length; i++) {
            row[i] = processColumnItem(row[i]);
        }
    }
}
