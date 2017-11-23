package com.myapplication.rick.packagedelivery

import org.junit.Test
import org.junit.Assert.*

/**
 * Created by Rick on 3-5-2016.
 */
class TestRangeType {

    @Test
    fun rangeUnevenToString() {
        val expected = "Uneven"
        assertEquals(expected, RangeType.Uneven.toString())
    }

    @Test
    fun rangeEvenToString() {
        val expected = "Even"
        assertEquals(expected, RangeType.Even.toString())
    }

    @Test
    fun rangeAllToString() {
        val expected = "All"
        assertEquals(expected, RangeType.All.toString())
    }
}
