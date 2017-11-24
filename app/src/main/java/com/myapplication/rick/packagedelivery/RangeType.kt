package com.myapplication.rick.packagedelivery

import android.os.Parcel
import android.os.Parcelable

enum class RangeType : Parcelable {
    Even,
    Uneven,
    All;

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(toString())
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<RangeType> {
        override fun createFromParcel(parcel: Parcel): RangeType =
                RangeType.valueOf(parcel.readString())

        override fun newArray(size: Int): Array<RangeType?> = arrayOfNulls(size)
    }
}
