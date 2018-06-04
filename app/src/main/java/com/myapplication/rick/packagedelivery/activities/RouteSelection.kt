package com.myapplication.rick.packagedelivery.activities

import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Toast
import com.myapplication.rick.packagedelivery.R
import com.myapplication.rick.packagedelivery.RouteFormat
import com.myapplication.rick.packagedelivery.RouteFormatReader

import kotlinx.android.synthetic.main.activity_route_selection.*
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class RouteSelection : Activity() {

    private val routeFormatReader: RouteFormatReader = RouteFormatReader()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_selection)

        val routes = readRoutes() ?: return
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, android.R.id.text1, routes)
        routesToSelect.adapter = adapter
        routesToSelect.setOnItemClickListener { adapterView, _, i, _ ->
            val routeFormat = adapterView.getItemAtPosition(i)
            if (routeFormat is RouteFormat)
            {
                Toast.makeText(this, getString(R.string.route_selected, routeFormat.toString()), Toast.LENGTH_SHORT).show()
                val intent = newIntent(this, routeFormat)
                startActivity(intent)
            }
        }
    }

    internal companion object {
        const val INTENT_ROUTE_FORMAT = "com.myapplication.rick.packagedelivery.routeFormat"

        private fun newIntent(context: Context, routeFormat: RouteFormat): Intent {
            val intent = Intent(context, RouteCreation::class.java)
            intent.putExtra(INTENT_ROUTE_FORMAT, routeFormat)
            return intent
        }
    }

    private fun readRoutes(): ArrayList<RouteFormat>? {
        try {
            val folder = File(this.getExternalFilesDir(null), "routes")
            println(folder.name)
            val routes = ArrayList<RouteFormat>()
            folder.walkTopDown().forEach {
                if (it.isFile) {
                    try {
                        routes.add(routeFormatReader.parseStreets(it))
                    } catch (ioEx: IOException) {
                        // this file might be invalid, skip it
                        println("Invalid file in folder: ${it.nameWithoutExtension}")
                        Toast.makeText(this, "Invalid file: ${it.nameWithoutExtension}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            folder.walkTopDown().forEach { if (it.isFile) println("Filename: ${it.name}") }
            routes.forEach { println("Route name: ${it.name}") }
            return routes
        } catch (fnfEx: FileNotFoundException) {
            fnfEx.printStackTrace()
            return null
        }
    }
}
