package com.myapplication.rick.packagedelivery;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Rick on 3-5-2016.
 */
public class TestRangeType {

    @Test
    public void rangeUnevenToString() {
        String expected = "Uneven";
        assertEquals(expected, RangeType.Uneven.toString());
    }

    @Test public void rangeEvenToString() {
        String expected = "Even";
        assertEquals(expected, RangeType.Even.toString());
    }

    @Test
    public void rangeAllToString() {
        String expected = "All";
        assertEquals(expected, RangeType.All.toString());
    }
}
