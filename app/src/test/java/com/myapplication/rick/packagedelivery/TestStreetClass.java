package com.myapplication.rick.packagedelivery;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Rick on 2-5-2016.
 */
public class TestStreetClass {
    private final String streetName;

    private final Range range;

    public TestStreetClass() {
        streetName  = "schoolstreet";
        final int lowerBoundStandard = 1;
        final int upperBoundStandard = 20;
        range = new Range(lowerBoundStandard, upperBoundStandard, RangeType.All);
    }
    @Test(expected = IllegalArgumentException.class)
    public void streetCreationWithNullRange() throws IllegalArgumentException {
        Range range = null;
        Street street = new Street(streetName, range);
    }


    @Test
    public void streetCreationValid() {
        Street street = new Street(streetName, range);
    }

    @Test
    public void checkStreetObjectParams() {
        Street street = new Street(streetName, range);
        assertEquals(streetName, street.getName());
        assertEquals(range, street.getRange());
    }

    @Test (expected = IllegalArgumentException.class)
    public void streetCreationNameWithOnlyWhitespaceShouldFail() throws IllegalArgumentException {
        String whitespace = "                              ";
        Street street = new Street(whitespace, range);
    }

    @Test (expected = IllegalArgumentException.class)
    public void streetCreationWithNullNameFails() throws IllegalArgumentException {
        Street street = new Street(null, range);
    }

    @Test
    public void toStringTest() {
        Street street = new Street(streetName, range);
        String expected = streetName + ", " + range.toString();
        assertEquals(expected, street.toString());
    }
}
