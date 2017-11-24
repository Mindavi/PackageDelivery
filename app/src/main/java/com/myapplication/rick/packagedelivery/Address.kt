package com.myapplication.rick.packagedelivery

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Rick on 7-5-2016.
 */
internal class Address
constructor(val street: Street, val number: Int): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(Street::class.java.classLoader),
            parcel.readInt())

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

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address = Address(parcel)

        override fun newArray(size: Int): Array<Address?> = arrayOfNulls(size)
    }
}
