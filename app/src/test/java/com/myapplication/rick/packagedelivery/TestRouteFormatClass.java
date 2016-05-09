package com.myapplication.rick.packagedelivery;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Rick on 2-5-2016.
 */
public class TestRouteFormatClass {
    private final RouteFormat standardRoute;
    private final Street standardStreet;

    public TestRouteFormatClass() {
        standardRoute = new RouteFormat();
        standardStreet = new Street("Street", new Range(1, 20, RangeType.All));
    }

    @Before
    public void reset() {
        standardRoute.reset();
    }

    @Test
    public void addStreet() {
        standardRoute.addStreet(standardStreet);

    }

    @Test
    public void totalStreets() {
        boolean result1 = standardRoute.addStreet(new Street("hello1", new Range(1, 9, RangeType.Uneven)));
        boolean result2 = standardRoute.addStreet(new Street("hello2", new Range(1, 9, RangeType.Uneven)));
        boolean result3 = standardRoute.addStreet(new Street("hello3", new Range(1, 9, RangeType.Uneven)));
        boolean result4 = standardRoute.addStreet(new Street("hello4", new Range(1, 9, RangeType.Uneven)));
        if (!(result1 &&
                result2 &&
                result3 &&
                result4)) {
            fail("Could not add one or more of the elements");
        }

        assertEquals(standardRoute.totalStreets(), 4);
    }

    @Test
    public void resetTest() {
        standardRoute.addStreet(standardStreet);
        standardRoute.reset();
        List<Street> streetListEmpty = new ArrayList<>();
        assertEquals(streetListEmpty, standardRoute.getRoute());
    }
}
