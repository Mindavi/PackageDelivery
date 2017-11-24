package com.myapplication.rick.packagedelivery

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

/**
 * Created by Rick on 1-5-2016.
 * Sorted list describing the order of a route
 */
internal class RouteFormat(val name: String, val route: ArrayList<Street> = ArrayList(75)): Parcelable {
    init {
        if (name.isEmpty()) throw IllegalArgumentException("Name mustn't be empty")
    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            arrayListOf<Street>().apply {
                parcel.readList(this, Street::class.java.classLoader)
            })

    fun addStreet(street: Street): Boolean =
            !route.contains(street) && route.add(street)

    fun removeStreet(street: Street): Boolean = route.remove(street)

    fun totalStreets(): Int = route.size

    fun reset() = route.clear()

    fun toCSVList(): ArrayList<Array<String>> = route.mapTo(ArrayList()) { it.toCSV() }

    override fun toString(): String = name
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<RouteFormat> {
        override fun createFromParcel(parcel: Parcel): RouteFormat = RouteFormat(parcel)

        override fun newArray(size: Int): Array<RouteFormat?> = arrayOfNulls(size = size)
    }
}
