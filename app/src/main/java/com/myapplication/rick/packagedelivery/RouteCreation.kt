package com.myapplication.rick.packagedelivery

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class RouteCreation : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_creation)
        val routeFormat = intent.getParcelableExtra<RouteFormat>(RouteSelection.INTENT_ROUTE_FORMAT)
        println(routeFormat.route)
    }
}
