package com.myapplication.rick.packagedelivery;

import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * Created by Rick on 1-5-2016.
 */
public class Range implements CSVWriteAble {
    private int lowerBound;
    private int upperBound;
    private RangeType rangeType;

    public Range(int lowerBound, int upperBound, @NonNull RangeType rangeType) throws IllegalArgumentException {
        if (lowerBound < 1) {
            throw new IllegalArgumentException("Illegal lower bound");
        }
        if (upperBound < 1) {
            throw new IllegalArgumentException("Illegal upper bound");
        }
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Lower bound is greater than upper bound");
        }

        if (rangeType == null) {
            throw new IllegalArgumentException("Range type not defined");
        }
        if (rangeType == RangeType.Uneven && (lowerBound % 2 == 0 || upperBound % 2 == 0)) {
            throw new IllegalArgumentException("Only uneven numbers are allowed, because only uneven is selected");
        }
        if (rangeType == RangeType.Even && (lowerBound % 2 != 0 || upperBound % 2 != 0)) {
            throw new IllegalArgumentException("Only even numbers are allowed, because only even is selected");
        }
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.rangeType = rangeType;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%d-%d, %s", lowerBound, upperBound, rangeType.toString());
    }

    public String[] toCSV() {
        return new String[] {String.valueOf(lowerBound), String.valueOf(upperBound) , rangeType.toString()};
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public RangeType getRangeType() {
        return rangeType;
    }

    @SuppressWarnings("SameReturnValue")
    public int length() {
        return 3;
    }
}

