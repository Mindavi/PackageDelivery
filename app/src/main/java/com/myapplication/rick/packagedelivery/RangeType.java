package com.myapplication.rick.packagedelivery;

public enum RangeType {
    Even,
    Uneven,
    All;

    public static RangeType getRangeTypeFromNumber(int number) {
        if (number % 2 == 0) {
            return Even;
        } else {
            return Uneven;
        }
    }
}
