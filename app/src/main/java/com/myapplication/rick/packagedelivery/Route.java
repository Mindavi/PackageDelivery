package com.myapplication.rick.packagedelivery;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Rick on 7-5-2016.
 */
class Route {
    private ArrayList<Address> addresses;
    private RouteFormat routeFormat;

    public Route(RouteFormat routeFormat) {
        this.addresses = new ArrayList<>();
        this.routeFormat = routeFormat;
    }

    public boolean addAddress(@NonNull Address address) throws IllegalArgumentException {
        if (address == null) {
            throw new IllegalArgumentException("Address is null");
        }

        for(Street street : routeFormat.getRoute()) {
            if (street == address.getStreet()) { //if street is in the routeformat
                addresses.add(address);
                return true;
            }
        }
        return false;
    }

    public void sort() {

    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }
}
