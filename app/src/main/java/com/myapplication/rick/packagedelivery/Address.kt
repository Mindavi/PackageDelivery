package com.myapplication.rick.packagedelivery

/**
 * Created by Rick on 7-5-2016.
 */
internal class Address
constructor(val street: Street, val number: Int) {
    init {
        if (number < street.range.lowerBound || number > street.range.upperBound) {
            throw IllegalArgumentException("Number too high or too low")
        }

        if (street.range.rangeType != RangeType.All) {
            if (street.range.rangeType != RangeType.getRangeTypeFromNumber(number)) {
                throw IllegalArgumentException("Number not corresponding to the rangetype")
            }
        }
    }
}
