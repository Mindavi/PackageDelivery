package com.myapplication.rick.packagedelivery

import android.os.Parcel
import android.os.Parcelable
import java.util.Locale

/**
 * Created by Rick on 1-5-2016.
 */
class Street(val name: String, val range: Range, val direction: Direction = Direction.LowToHigh) : CSVWriteAble, Parcelable {
    init {
        if (name.isEmpty()) throw IllegalArgumentException("Empty name")
    }

    override fun toString(): String = "$name, $range"

    override fun toCSV(): Array<String> {
        val values = ArrayList(range.toCSV().asList())
        values.add(0, name)
        values.add(direction.toString())
        return values.toTypedArray()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeParcelable(range, flags)
        parcel.writeParcelable(direction, flags)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Street> {
            override fun newArray(size: Int): Array<Street?> = arrayOfNulls<Street?>(size)

            override fun createFromParcel(parcel: Parcel): Street {
                val name: String = parcel.readString()
                val range: Range = parcel.readParcelable(Range::class.java.classLoader)
                val direction: Direction = parcel.readParcelable(Direction::class.java.classLoader)
                return Street(name, range, direction)
            }
        }
    }
}
