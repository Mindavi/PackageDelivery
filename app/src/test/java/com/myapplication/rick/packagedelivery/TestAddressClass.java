package com.myapplication.rick.packagedelivery;

import org.junit.Test;

/**
 * Created by Rick on 8-5-2016.
 */
public class TestAddressClass {
    private Street unevenStreet = new Street("unevenstreet", new Range(3, 9, RangeType.Uneven));
    private Street evenStreet = new Street("evenstreet", new Range(4, 30, RangeType.Even));

    @Test
    public void AddressCreationUnevenMinimumValue() {
        Address address = new Address(unevenStreet, 3);
    }

    @Test
    public void AddressCreationUnevenMaximumValue() {
        Address address = new Address(unevenStreet, 9);
    }

    @Test (expected = IllegalArgumentException.class)
    public void AddressCreationEvenNumberWithUnevenStreet() throws IllegalArgumentException {
        Address address = new Address(unevenStreet, 4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void AddressCreationUnevenTooHighNumber () throws IllegalArgumentException {
        Address address = new Address(unevenStreet, 11);
    }

    @Test (expected = IllegalArgumentException.class)
    public void AddressCreationUnevenTooLowNumber() throws IllegalArgumentException {
        Address address = new Address(unevenStreet, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void AddressCreationUnevenNumberWithEvenStreet() throws IllegalArgumentException {
        Address address = new Address(evenStreet, 9);
    }

    @Test (expected = IllegalArgumentException.class)
    public void AddressCreationNullStreet() throws IllegalArgumentException {
        Address address = new Address(null, 1);
    }
}
