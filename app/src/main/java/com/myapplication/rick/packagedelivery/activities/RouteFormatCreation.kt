package com.myapplication.rick.packagedelivery.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.myapplication.rick.packagedelivery.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

import java.io.IOException
import java.util.ArrayList

class RouteFormatCreation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_save_format.setOnClickListener({ _ ->
            val streets = ArrayList<Street>()
            streets.add(Street("Trentstraat", Range(1, 40, RangeType.All), Direction.HighToLow))
            streets.add(Street("Leekbusweg", Range(1, 15, RangeType.All)))
            streets.add(Street("Langeboomstraat", Range(1, 89, RangeType.Uneven)))
            streets.add(Street("Langeboomstraat", Range(2, 110, RangeType.Even), Direction.HighToLow))
            streets.add(Street("Vingerhoedspat", Range(1, 2, RangeType.All)))

            val folder = File(this.getExternalFilesDir(null), "routes")
            val routeFormat = RouteFormat("Test 4 Rick", streets)
            val routeFormatWriter = RouteFormatWriter()
            try {
                routeFormatWriter.writeStreets(routeFormat, folder)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })
        btn_load_format.setOnClickListener({ _ ->
            fillView()
        })
    }

    private fun noRouteFormatFound() {
        val textView = TextView(this)
        textView.text = getString(R.string.no_route_format_file)
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
        val folder = File(this.getExternalFilesDir(null), "routes")
        val routeFiles = folder.listFiles()
        if (routeFiles.count() < 1) return null // there are no files
        val routeFormatReader = RouteFormatReader()
        for (routeFile in routeFiles) {
            println(routeFile.absolutePath)
            try {
                return routeFormatReader.parseStreets(routeFile)
            } catch(e: IOException) {
                // probably invalid file, try next
                println(e.message)
            }
        }
        return null
    }
}
