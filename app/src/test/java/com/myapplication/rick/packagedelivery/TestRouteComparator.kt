package com.myapplication.rick.packagedelivery

import org.junit.Before
import org.junit.Test

import java.util.ArrayList

import org.junit.Assert.*
import java.security.AllPermission

/**
 * Created by Rick on 2-5-2016.
 */
class TestRouteComparator {
    private val trentstraat: Street = Street("Trentstraat", Range(1, 40, RangeType.All), Direction.HighToLow)
    private val schoondonkseweg: Street = Street("Schoondonkseweg", Range(2, 42, RangeType.Even), Direction.LowToHigh)
    private val grassstreet: Street = Street("Grassstreet", Range(1, 19, RangeType.All))
    private val routeFormat: RouteFormat = RouteFormat("Veldhoven 3", arrayListOf(trentstraat, schoondonkseweg, grassstreet))
    private var route: Route = Route(routeFormat)

    // sorted addresses
    private val address1 = Address(trentstraat, 21)
    private val address2 = Address(trentstraat, 15)
    private val address3 = Address(schoondonkseweg, 2)
    private val address4 = Address(grassstreet, 10)

    @Before
    fun setupRoute() {
        route = Route(routeFormat)
        route.addAddress(address4)
        route.addAddress(address2)
        route.addAddress(address3)
        route.addAddress(address1)
    }

    @Test
    fun testAddressesAreSorted() {
        assertEquals(address1.street.name, route.addresses[0].street.name)
        assertEquals(address1.number, route.addresses[0].number)

        assertEquals(address2.street.name, route.addresses[1].street.name)
        assertEquals(address2.number, route.addresses[1].number)

        assertEquals(address3.street.name, route.addresses[2].street.name)
        assertEquals(address3.number, route.addresses[2].number)

        assertEquals(address4.street.name, route.addresses[3].street.name)
        assertEquals(address4.number, route.addresses[3].number)
    }
}
