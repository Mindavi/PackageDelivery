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
internal class RouteFormatReader(private val context: Context) {
    @Throws(IOException::class)
    fun parseStreets(filename: String): ArrayList<Street> {
        val folder = File(context.getExternalFilesDir(null),"PackageDelivery")
        val file = File(folder, filename)

        val streets = ArrayList<Street>()
        val reader = CSVReader(FileReader(file))

        val content = reader.readAll()

        for (row in content) {
            if (row.size == 4) {
                val name = row[0]
                val range_low = Integer.parseInt(row[1])
                val range_high = Integer.parseInt(row[2])
                val range_type = RangeType.valueOf(row[3])
                val range = Range(range_low, range_high, range_type)
                streets.add(Street(name, range))
            } else {
                throw IOException("File is not valid")
            }
        }
        return streets
    }
}
