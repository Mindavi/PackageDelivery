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
    }

    fun onSaveFormatClick(view: View) {
        val streets = ArrayList<Street>()
        streets.add(Street("Trentstraat", Range(1, 40, RangeType.All), Direction.HighToLow))
        streets.add(Street("Leekbusweg", Range(1, 15, RangeType.All)))
        streets.add(Street("Langeboomstraat", Range(1, 89, RangeType.Uneven)))
        streets.add(Street("Langeboomstraat", Range(2, 110, RangeType.Even), Direction.HighToLow))
        streets.add(Street("Vingerhoedspat", Range(1,2, RangeType.All)));

        val routeFormat = RouteFormat("Test 4 Rick", streets)
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
        scroll_layout.removeAllViews()
        for (street in routeFormat.route) {
            val textView = TextView(this)
            textView.text = "${street.name}, ${street.range}, ${street.direction}"
            scroll_layout.addView(textView)
        }
    }

    private fun readRouteFormat(): RouteFormat? {
        val routeFormatReader = RouteFormatReader(this)
        val routeFormat : RouteFormat
        try {
            routeFormat = routeFormatReader.parseStreets("Lelystad 3 Rick.csv")
            return routeFormat
        } catch(e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun onLoadFormatClick(view: View) {
        fillView()
    }
}
