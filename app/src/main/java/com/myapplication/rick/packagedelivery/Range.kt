package com.myapplication.rick.packagedelivery

import java.util.Locale

/**
 * Created by Rick on 1-5-2016.
 */
class Range
constructor(val lowerBound: Int, val upperBound: Int, val rangeType: RangeType) : CSVWriteAble {

    init {
        if (lowerBound < 1) {
            throw IllegalArgumentException("Illegal lower bound")
        }
        if (upperBound < 1) {
            throw IllegalArgumentException("Illegal upper bound")
        }
        if (lowerBound > upperBound) {
            throw IllegalArgumentException("Lower bound is greater than upper bound")
        }
        if (rangeType == RangeType.Uneven && (lowerBound % 2 == 0 || upperBound % 2 == 0)) {
            throw IllegalArgumentException("Only uneven numbers are allowed, because only uneven is selected")
        }
        if (rangeType == RangeType.Even && (lowerBound % 2 != 0 || upperBound % 2 != 0)) {
            throw IllegalArgumentException("Only even numbers are allowed, because only even is selected")
        }
    }

    override fun toString(): String {
        return "$lowerBound-$upperBound, $rangeType"
        //return String.format(Locale.US, "%d-%d, %s", lowerBound, upperBound, rangeType.toString())
    }

    override fun toCSV(): Array<String> {
        return arrayOf(lowerBound.toString(), upperBound.toString(), rangeType.toString())
    }

//    fun length(): Int {
//        return 3
//    }
}

