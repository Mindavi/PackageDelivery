package com.myapplication.rick.packagedelivery

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Rick on 7-5-2016.
 */
class Address(val street: Street, val number: Int): Parcelable {
    init {
        if (number < street.range.lowerBound || number > street.range.upperBound) {
            throw IllegalArgumentException("Number too high or too low")
        }

        if (street.range.rangeType != RangeType.All) {
            val rangeType : RangeType = if (number % 2 == 0) RangeType.Even else RangeType.Uneven
            if (street.range.rangeType != rangeType) {
                throw IllegalArgumentException("Number not corresponding to the rangetype")
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(street, flags)
        parcel.writeInt(number)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Address> {
            override fun createFromParcel(parcel: Parcel): Address {
                val street: Street = parcel.readParcelable(Street::class.java.classLoader)
                val number: Int = parcel.readInt()
                return Address(street, number)
            }
            override fun newArray(size: Int): Array<Address?> = arrayOfNulls(size)
        }
    }
}
