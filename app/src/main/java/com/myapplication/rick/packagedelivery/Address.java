package com.myapplication.rick.packagedelivery;

import android.support.annotation.NonNull;

/**
 * Created by Rick on 7-5-2016.
 */
class Address {
    private Street street;
    private int number;
    public Address(@NonNull Street street, int number) throws IllegalArgumentException {
        if (street == null) {
            throw new IllegalArgumentException("Street is null");
        }
        if (number < street.getRange().getLowerBound() || number > street.getRange().getUpperBound()) {
            throw new IllegalArgumentException("Number too high or too low");
        }

        if (street.getRange().getRangeType() != RangeType.All) {
            if (street.getRange().getRangeType() != RangeType.getRangeTypeFromNumber(number)) {
                throw new IllegalArgumentException("Number not corresponding to the rangetype");
            }
        }
    }

    public Street getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }
}
