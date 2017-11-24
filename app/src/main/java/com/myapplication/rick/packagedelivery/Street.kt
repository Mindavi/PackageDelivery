package com.myapplication.rick.packagedelivery

import android.os.Parcel
import android.os.Parcelable
import java.util.Locale

/**
 * Created by Rick on 1-5-2016.
 */
internal class Street() : CSVWriteAble, Parcelable {
    lateinit var name: String
        private set
    lateinit var range: Range
        private set
    lateinit var direction: Direction
        private set

    constructor(parcel: Parcel) : this() {
        this.name = parcel.readString()
        this.range = parcel.readParcelable(Range::class.java.classLoader)
        this.direction = parcel.readParcelable(Direction::class.java.classLoader)
    }

    @Throws(IllegalArgumentException::class)
    constructor(name: String, range: Range) : this() {
        if (name.isBlank()) {
            throw IllegalArgumentException("Empty name")
        }

        this.name = name
        this.range = range
        this.direction = Direction.LowToHigh //default value
    }

    @Throws(IllegalArgumentException::class)
    constructor(name: String, range: Range, direction: Direction): this() {
        if (name.isEmpty()) {
            throw IllegalArgumentException("Empty name")
        }

        this.name = name
        this.range = range
        this.direction = direction
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

    companion object CREATOR : Parcelable.Creator<Street> {
        override fun createFromParcel(parcel: Parcel): Street = Street(parcel)

        override fun newArray(size: Int): Array<Street?> = arrayOfNulls(size)
    }
}
