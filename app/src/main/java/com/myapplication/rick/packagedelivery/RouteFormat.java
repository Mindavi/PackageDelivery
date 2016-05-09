package com.myapplication.rick.packagedelivery;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Rick on 1-5-2016.
 */
class RouteFormat {
    private final ArrayList<Street> route;
    public RouteFormat() {
        route = new ArrayList<>(75);
    }

    public RouteFormat(@NonNull ArrayList<Street> route) {
        this.route = route;
    }

    public ArrayList<Street> getRoute() {
        return route;
    }

    //if street is not null and route does not contain the street, add
    public boolean addStreet(Street street) {
        return street != null && !route.contains(street) && route.add(street);
    }

    /*
    public boolean removeStreet(Street street) {
        return route.remove(street);
    }*/

    public int totalStreets() {
        return route.size();
    }

    public void reset() {
        route.clear();
    }

    public ArrayList<String[]> toCSVList() {
        ArrayList<String[]> csvStreets = new ArrayList<>();
        for (Street street : route) {
            csvStreets.add(street.toCSV());
        }
        return csvStreets;
    }
}
