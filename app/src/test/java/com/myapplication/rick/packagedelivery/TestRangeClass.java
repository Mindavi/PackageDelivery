package com.myapplication.rick.packagedelivery;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestRangeClass {
    private final int lowerBoundUneven;
    private final int upperBoundUneven;
    private final int lowerBoundEven;
    private final int upperBoundEven;
    private final int zeroLowerBound;
    private final int zeroUpperBound;

    public TestRangeClass() {
        lowerBoundUneven = 1;
        upperBoundUneven = 89;
        lowerBoundEven = 2;
        upperBoundEven = 90;
        zeroLowerBound = 0;
        zeroUpperBound = 0;
    }

    @Test
    public void rangeCreationUnevenValid() {
        Range range = new Range(lowerBoundUneven, upperBoundUneven, RangeType.Uneven);
    }

    @Test
    public void rangeCreationEvenValid() {
        Range range = new Range(lowerBoundEven, upperBoundEven, RangeType.Even);
    }
    @Test
    public void rangeCreationAllNumbersValid() {
        Range range = new Range(lowerBoundEven, upperBoundUneven, RangeType.All);
    }

    @Test
    public void getMethodsAndAllParametersSetRight() {
        Range range = new Range(lowerBoundEven, upperBoundUneven, RangeType.All);
        assertEquals(range.getLowerBound(), lowerBoundEven);
        assertEquals(range.getUpperBound(), upperBoundUneven);
        assertEquals(range.getRangeType(), RangeType.All);
    }

    @Test (expected = IllegalArgumentException.class)
    public void rangeCreationInvalidLowerBound() throws IllegalArgumentException {
        Range range = new Range(zeroLowerBound, upperBoundUneven, RangeType.All);
    }

    @Test (expected = IllegalArgumentException.class)
    public void rangeCreationInvalidUpperBound() throws IllegalArgumentException {
        Range range = new Range(lowerBoundEven, zeroUpperBound, RangeType.All);
    }
    @Test (expected = IllegalArgumentException.class)
    public void rangeCreationLowerBoundGreaterThanUpperBound() throws IllegalArgumentException {
        Range range = new Range(upperBoundEven, lowerBoundEven, RangeType.All);
    }
    @Test (expected = IllegalArgumentException.class)
    public void rangeCreationLowerBoundEvenWithRangeTypeUneven() throws IllegalArgumentException {
        Range range = new Range(lowerBoundEven, upperBoundUneven, RangeType.Uneven);
    }
    @Test (expected = IllegalArgumentException.class)
    public void rangeCreationLowerBoundUnevenWithRangeTypeEven() throws IllegalArgumentException {
        Range range = new Range(lowerBoundUneven, upperBoundEven, RangeType.Even);
    }
    @Test (expected = IllegalArgumentException.class)
    public void rangeCreationWithNullRangeType() throws IllegalArgumentException {
        Range range = new Range(lowerBoundEven, upperBoundEven, null);
    }
}