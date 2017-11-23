package com.myapplication.rick.packagedelivery

import org.junit.Test
import org.junit.Assert.*

/**
 * Created by Rick on 2-5-2016.
 */
class TestStreetClass {
    private val streetName: String

    private val range: Range

    init {
        streetName = "schoolstreet"
        val lowerBoundStandard = 1
        val upperBoundStandard = 20
        range = Range(lowerBoundStandard, upperBoundStandard, RangeType.All)
    }

    @Test
    fun streetCreationValid() {
        val street = Street(streetName, range)
    }

    @Test
    fun checkStreetObjectParams() {
        val street = Street(streetName, range)
        assertEquals(streetName, street.name)
        assertEquals(range, street.range)
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(IllegalArgumentException::class)
    fun streetCreationNameWithOnlyWhitespaceShouldFail() {
        val whitespace = "                              "
        val street = Street(whitespace, range)
    }


    @Test
    fun toStringTest() {
        val street = Street(streetName, range)
        val expected = streetName + ", " + range.toString()
        assertEquals(expected, street.toString())
    }
}
