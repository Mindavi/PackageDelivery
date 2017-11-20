package com.myapplication.rick.packagedelivery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

import java.io.IOException
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fillView()
    }

    fun button_click(view: View) {
        val streets = ArrayList<Street>()
        streets.add(Street("Trentstraat", Range(1, 40, RangeType.All)))
        streets.add(Street("Leekbusweg", Range(1, 15, RangeType.All)))
        streets.add(Street("Langeboomstraat", Range(1, 89, RangeType.Uneven)))

        val routeFormat = RouteFormat(streets)
        val routeFormatWriter = RouteFormatWriter(this)
        try {
            routeFormatWriter.writeStreets(routeFormat)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun noRouteFormatFound() {
        val textView = TextView(this)
        textView.text = "No route format file found"
        scroll_layout.addView(textView)
    }

    private fun fillView() {
        val routeFormat: RouteFormat? = readRouteFormat()
        if (routeFormat == null) {
            noRouteFormatFound()
            return
        }
        for (street in routeFormat.route) {
            val textView = TextView(this)
            textView.text = "Name:${street.name}, Range:${street.range}"
            scroll_layout.addView(textView)
        }
    }

    private fun readRouteFormat(): RouteFormat? {
        val routeFormatReader = RouteFormatReader(this)
        val routeFormat : RouteFormat?
        try {
            routeFormat = routeFormatReader.parseStreets("myFile.csv")
            println("$routeFormat")
            return routeFormat
        } catch(e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun button2_click(view: View) {
        val routeFormatReader = RouteFormatReader(this)
        val routeFormat : RouteFormat
        try {
            routeFormat = routeFormatReader.parseStreets("myFile.csv")
            println("$routeFormat")
        } catch(e: IOException) {
            e.printStackTrace()
        }
    }
}
