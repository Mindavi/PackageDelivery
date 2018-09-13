package com.myapplication.rick.packagedelivery

import org.junit.Assert
import org.junit.Test

/**
 * Created by Rick on 3-5-2016.
 */
class TestRouteClass {

    @Test
    fun testAddAddress() {
        val street = Street("Test street", Range(1, 10, RangeType.All))
        val format = RouteFormat("Test route")
        format.addStreet(street)

        val route = Route(format)
        val address = Address(street,1)
        route.addAddress(address)

        Assert.assertTrue(route.addresses.contains(address))
    }

    @Test
    fun testAddAddressWithCopiedStreet() {
        val street = Street("Test street", Range(1, 10, RangeType.All))
        val format = RouteFormat("Test route")
        format.addStreet(street)

        val route = Route(format)
        val streetCopy = Street(street.name, street.range, street.direction)
        val address = Address(streetCopy,1)
        route.addAddress(address)

        Assert.assertTrue(route.addresses.contains(address))
    }
}
