package com.myapplication.rick.packagedelivery

import org.junit.Test
import org.junit.Assert.*

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
class TestRangeClass {
    private val lowerBoundUneven: Int
    private val upperBoundUneven: Int
    private val lowerBoundEven: Int
    private val upperBoundEven: Int
    private val zeroLowerBound: Int
    private val zeroUpperBound: Int

    init {
        lowerBoundUneven = 1
        upperBoundUneven = 89
        lowerBoundEven = 2
        upperBoundEven = 90
        zeroLowerBound = 0
        zeroUpperBound = 0
    }

    @Test
    fun rangeCreationUnevenValid() {
        val range = Range(lowerBoundUneven, upperBoundUneven, RangeType.Uneven)
    }

    @Test
    fun rangeCreationEvenValid() {
        val range = Range(lowerBoundEven, upperBoundEven, RangeType.Even)
    }

    @Test
    fun rangeCreationAllNumbersValid() {
        val range = Range(lowerBoundEven, upperBoundUneven, RangeType.All)
    }

    @Test
    fun getMethodsAndAllParametersSetRight() {
        val range = Range(lowerBoundEven, upperBoundUneven, RangeType.All)
        assertEquals(range.lowerBound.toLong(), lowerBoundEven.toLong())
        assertEquals(range.upperBound.toLong(), upperBoundUneven.toLong())
        assertEquals(range.rangeType, RangeType.All)
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(IllegalArgumentException::class)
    fun rangeCreationInvalidLowerBound() {
        val range = Range(zeroLowerBound, upperBoundUneven, RangeType.All)
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(IllegalArgumentException::class)
    fun rangeCreationInvalidUpperBound() {
        val range = Range(lowerBoundEven, zeroUpperBound, RangeType.All)
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(IllegalArgumentException::class)
    fun rangeCreationLowerBoundGreaterThanUpperBound() {
        val range = Range(upperBoundEven, lowerBoundEven, RangeType.All)
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(IllegalArgumentException::class)
    fun rangeCreationLowerBoundEvenWithRangeTypeUneven() {
        val range = Range(lowerBoundEven, upperBoundUneven, RangeType.Uneven)
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(IllegalArgumentException::class)
    fun rangeCreationLowerBoundUnevenWithRangeTypeEven() {
        val range = Range(lowerBoundUneven, upperBoundEven, RangeType.Even)
    }
}