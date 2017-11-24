package com.myapplication.rick.packagedelivery

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by Rick on 7-5-2016.
 */
internal class Route(private val routeFormat: RouteFormat): Parcelable {
    val addresses: ArrayList<Address> = ArrayList()
    val comparator: AddressComparator = AddressComparator(routeFormat)

    constructor(parcel: Parcel) : this(parcel.readParcelable<RouteFormat>(RouteFormat::class.java.classLoader))

    @Throws(IllegalArgumentException::class)
    fun addAddress(address: Address): Boolean {
        for (street in routeFormat.route) {
            if (street == address.street) { //if street is in the routeformat
                addresses.add(address)
                return true
            }
        }
        return false
    }

    fun sort() {
        Collections.sort(addresses, comparator)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(routeFormat, flags)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Route> {
        override fun createFromParcel(parcel: Parcel): Route = Route(parcel)

        override fun newArray(size: Int): Array<Route?> = arrayOfNulls(size)
    }
}
