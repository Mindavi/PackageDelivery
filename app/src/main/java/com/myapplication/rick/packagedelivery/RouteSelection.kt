package com.myapplication.rick.packagedelivery

import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_route_selection.*
import java.io.File
import java.io.FileNotFoundException

class RouteSelection : Activity() {

    private val routeFormatReader: RouteFormatReader = RouteFormatReader(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_selection)

        val routes = readRoutes() ?: return
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, android.R.id.text1, routes)
        routesToSelect.adapter = adapter
        routesToSelect.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show()
            // TODO: create new activity with route
        }
    }

    internal companion object {

        private val INTENT_ROUTE_FORMAT = "user_id"

        fun newIntent(context: Context, routeFormat: RouteFormat): Intent {
            val intent = Intent(context, RouteSelection::class.java)
            //intent.putExtra(INTENT_ROUTE_FORMAT, routeFormat)
            return intent
        }
    }

    private fun readRoutes(): ArrayList<RouteFormat>? {
        try {
            val folder = File(this.getExternalFilesDir(null), "Routes")
            val routes = ArrayList<RouteFormat>()
            folder.walkTopDown().forEach {
                if (it.isFile) routes.add(routeFormatReader.parseStreets(it.name))
            }
            folder.walkTopDown().forEach { if (it.isFile) println("Filename: ${it.name}") }
            routes.forEach { println("Route name: ${it.name}") }
            return routes
        } catch (fnfEx: FileNotFoundException) {
            fnfEx.printStackTrace()
            return null
        }
    }

    fun fillRoutes() {
        //
    }

}
