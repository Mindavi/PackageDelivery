package com.myapplication.rick.packagedelivery

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by Rick on 7-5-2016.
 */
internal class Route(private val routeFormat: RouteFormat) {
    val addresses: ArrayList<Address> = ArrayList()
    val comparator: AddressComparator = AddressComparator(routeFormat)

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
}
