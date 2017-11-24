package com.myapplication.rick.packagedelivery

import org.junit.Before
import org.junit.Test

import java.util.ArrayList

import org.junit.Assert.*

/**
 * Created by Rick on 2-5-2016.
 */
class TestRouteFormatClass {
    private val standardRoute: RouteFormat
    private val standardStreet: Street

    init {
        standardRoute = RouteFormat("Standard")
        standardStreet = Street("Street", Range(1, 20, RangeType.All))
    }

    @Before
    fun reset() {
        standardRoute.reset()
    }

    @Test
    fun addStreet() {
        standardRoute.addStreet(standardStreet)

    }

    @Test
    fun totalStreets() {
        val result1 = standardRoute.addStreet(Street("hello1", Range(1, 9, RangeType.Uneven)))
        val result2 = standardRoute.addStreet(Street("hello2", Range(1, 9, RangeType.Uneven)))
        val result3 = standardRoute.addStreet(Street("hello3", Range(1, 9, RangeType.Uneven)))
        val result4 = standardRoute.addStreet(Street("hello4", Range(1, 9, RangeType.Uneven)))
        if (!(result1 &&
                result2 &&
                result3 &&
                result4)) {
            fail("Could not add one or more of the elements")
        }

        assertEquals(standardRoute.totalStreets().toLong(), 4)
    }

    @Test
    fun resetTest() {
        standardRoute.addStreet(standardStreet)
        standardRoute.reset()
        val streetListEmpty = ArrayList<Street>()
        assertEquals(streetListEmpty, standardRoute.route)
    }
}
