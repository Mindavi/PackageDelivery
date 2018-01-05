package com.myapplication.rick.packagedelivery

import android.content.Context

import com.opencsv.CSVWriter

import java.io.File
import java.io.FileWriter
import java.io.IOException

/**
 * Created by Rick on 5-5-2016.
 */
internal class RouteFormatWriter {
    @Throws(IOException::class)
    fun writeStreets(routeFormat: RouteFormat, folder: File) {
        val path = File(folder, "${routeFormat.name}.csv")
        println(folder.toString())
        if (folder.mkdirs()) {
            println("Success")
        } else {
            println("Folder not created or already exists")
        }
        println("Does folder exist: " + folder.exists())
        val writer = CSVWriter(FileWriter(path))
        writer.writeAll(routeFormat.toCSVList())
        writer.close()
    }
}
