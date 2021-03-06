package com.myapplication.rick.packagedelivery

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by Rick on 7-5-2016.
 */
internal class Route(val routeFormat: RouteFormat) : Parcelable {
    val addresses: ArrayList<Address> = ArrayList()
    private val comparator: AddressComparator = AddressComparator(routeFormat)

    @Throws(IllegalArgumentException::class)
    fun addAddress(address: Address): Boolean {
        if (addresses.contains(address)) {
            return false
        }
        if (routeFormat.route.contains(address.street)) {
            addresses.add(address)
            sort()
            return true
        }
        return false
    }

    private fun sort() {
        Collections.sort(addresses, comparator)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(addresses)
        parcel.writeParcelable(routeFormat, flags)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Route> {
        override fun createFromParcel(parcel: Parcel): Route {
            val route = Route(parcel.readParcelable(RouteFormat::class.java.classLoader))
            val list = ArrayList<Address>()
            parcel.readTypedList(list, Address.CREATOR)
            list.forEach { route.addAddress(it) }
            return route
        }

        override fun newArray(size: Int): Array<Route?> = arrayOfNulls(size = size)
    }
}
