package com.myapplication.rick.packagedelivery

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Rick on 3-5-2016.
 */
enum class Direction() : Parcelable {
    LowToHigh,
    HighToLow;

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(toString())
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Direction> {
        override fun createFromParcel(parcel: Parcel): Direction = Direction.valueOf(parcel.readString())

        override fun newArray(size: Int): Array<Direction?> = arrayOfNulls(size)
    }
}
