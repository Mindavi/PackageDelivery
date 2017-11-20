package com.myapplication.rick.packagedelivery

import java.util.ArrayList

/**
 * Created by Rick on 7-5-2016.
 */
internal class Route(private val routeFormat: RouteFormat) {
    val addresses: ArrayList<Address>

    init {
        this.addresses = ArrayList()
    }

    @Throws(IllegalArgumentException::class)
    fun addAddress(address: Address): Boolean {
        if (address == null) {
            throw IllegalArgumentException("Address is null")
        }

        for (street in routeFormat.route) {
            if (street == address.street) { //if street is in the routeformat
                addresses.add(address)
                return true
            }
        }
        return false
    }

    fun sort() {

    }
}
