package com.myapplication.rick.packagedelivery

import org.junit.Test

/**
 * Created by Rick on 8-5-2016.
 */
class TestAddressClass {
    private val unevenStreet = Street("unevenStreet", Range(3, 9, RangeType.Uneven))
    private val evenStreet = Street("evenStreet", Range(4, 30, RangeType.Even))

    @Test
    fun AddressCreationUnevenMinimumValue() {
        val address = Address(unevenStreet, 3)
    }

    @Test
    fun AddressCreationUnevenMaximumValue() {
        val address = Address(unevenStreet, 9)
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(IllegalArgumentException::class)
    fun AddressCreationEvenNumberWithUnevenStreet() {
        val address = Address(unevenStreet, 4)
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(IllegalArgumentException::class)
    fun AddressCreationUnevenTooHighNumber() {
        val address = Address(unevenStreet, 11)
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(IllegalArgumentException::class)
    fun AddressCreationUnevenTooLowNumber() {
        val address = Address(unevenStreet, 1)
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(IllegalArgumentException::class)
    fun AddressCreationUnevenNumberWithEvenStreet() {
        val address = Address(evenStreet, 9)
    }
}
