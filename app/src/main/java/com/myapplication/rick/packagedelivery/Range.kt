package com.myapplication.rick.packagedelivery

import android.os.Parcel
import android.os.Parcelable
import java.util.Locale

/**
 * Created by Rick on 1-5-2016.
 */
class Range(val lowerBound: Int, val upperBound: Int, val rangeType: RangeType) : CSVWriteAble, Parcelable {
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
    }

    override fun toCSV(): Array<String> {
        return arrayOf(lowerBound.toString(), upperBound.toString(), rangeType.toString())
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(lowerBound)
        parcel.writeInt(upperBound)
        parcel.writeParcelable(rangeType, flags)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Range> {
            override fun createFromParcel(parcel: Parcel): Range = Range(parcel.readInt(), parcel.readInt(), parcel.readParcelable(RangeType::class.java.classLoader))
            override fun newArray(size: Int): Array<Range?> = arrayOfNulls(size)
        }
    }
}

