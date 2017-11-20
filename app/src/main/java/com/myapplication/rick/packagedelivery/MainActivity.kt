package com.myapplication.rick.packagedelivery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import java.io.IOException
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun button_click(view: View) {
        val streets = ArrayList<Street>()
        streets.add(Street("Trentstraat", Range(1, 40, RangeType.All)))
        streets.add(Street("Leekbusweg", Range(1, 15, RangeType.All)))
        streets.add(Street("teststraat", Range(1, 89, RangeType.Uneven)))

        val routeFormat = RouteFormat(streets)
        val routeFormatWriter = RouteFormatWriter(this)
        try {
            routeFormatWriter.writeStreets(routeFormat)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun button2_click(view: View) {
        val routeFormatReader = RouteFormatReader(this)
        val routeFormat : ArrayList<Street>
        try {
            routeFormat = routeFormatReader.parseStreets("myFile.csv")
            println("$routeFormat")
        } catch(e: IOException) {
            e.printStackTrace()
        }
    }
}
