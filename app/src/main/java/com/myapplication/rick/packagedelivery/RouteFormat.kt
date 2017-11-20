package com.myapplication.rick.packagedelivery

import java.util.ArrayList

/**
 * Created by Rick on 1-5-2016.
 */
internal class RouteFormat {
    val route: ArrayList<Street>

    constructor() {
        route = ArrayList(75)
    }

    constructor(route: ArrayList<Street>) {
        this.route = route
    }

    //if street is not null and route does not contain the street, add
    fun addStreet(street: Street?): Boolean {
        return street != null && !route.contains(street) && route.add(street)
    }

    /*
    public boolean removeStreet(Street street) {
        return route.remove(street);
    }*/

    fun totalStreets(): Int {
        return route.size
    }

    fun reset() {
        route.clear()
    }

    fun toCSVList(): ArrayList<Array<String>> {
        val csvStreets = ArrayList<Array<String>>()
        for (street in route) {
            csvStreets.add(street.toCSV())
        }
        return csvStreets
    }
}
