package com.myapplication.rick.packagedelivery;

import android.support.annotation.NonNull;

import java.util.Locale;
/**
 * Created by Rick on 1-5-2016.
 */
class Street implements CSVWriteAble {
    private String name;
    private Range range;
    private Direction direction;

    public Street(@NonNull String name, @NonNull Range range) throws IllegalArgumentException {
        if (name == null || name.trim().length() < 1) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (range == null) {
            throw new IllegalArgumentException("Invalid range");
        }

        this.name = name;
        this.range = range;
        this.direction = Direction.LowToHigh; //default value
    }

    public Street(@NonNull String name, @NonNull Range range, @NonNull Direction direction) throws IllegalArgumentException {
        if (name == null || name.trim().length() < 1) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (range == null) {
            throw new IllegalArgumentException("Invalid range");
        }
        if (direction == null) {
            throw new IllegalArgumentException("Invalid direction");
        }

        this.name = name;
        this.range = range;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s, %s", name, range.toString());
    }

    public String[] toCSV() {
        String[] rangeValues = range.toCSV();
        String[] values = new String[1 + range.length()];
        values[0] = name;
        //should be same as arraycopy
        //for (int i = 0; i < range.length(); i++) {
        //values[i + 1] = rangeValues[i];
        //}
        System.arraycopy(rangeValues, 0, values, 1, rangeValues.length);
        return values;
    }

    public Range getRange() {
        return range;
    }

    public String getName() {
        return name;
    }

    //public Direction getDirection() {
    //    return direction;
    //}
}
