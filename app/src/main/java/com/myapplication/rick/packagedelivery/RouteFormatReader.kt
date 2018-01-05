package com.myapplication.rick.packagedelivery

import android.content.Context
import com.opencsv.*
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.util.ArrayList

/**
 * Created by Rick on 3-5-2016.
 */
internal class RouteFormatReader {
    @Throws(IOException::class)
    fun parseStreets(routeFile: File): RouteFormat {
        val streets = ArrayList<Street>()
        val reader = CSVReader(FileReader(routeFile))

        val content = reader.readAll()

        for (row in content) {
            //println("Row size: ${row.size}")
            if (row.size == 5) {
                val name = row[0]
                val range_low = Integer.parseInt(row[1])
                val range_high = Integer.parseInt(row[2])
                val range_type = RangeType.valueOf(row[3])
                val direction = Direction.valueOf(row[4])
                val range = Range(range_low, range_high, range_type)
                streets.add(Street(name, range, direction))
            } else {
                throw IOException("File is not valid")
            }
        }
        val name = routeFile.nameWithoutExtension
        return RouteFormat(name, streets)
    }
}
