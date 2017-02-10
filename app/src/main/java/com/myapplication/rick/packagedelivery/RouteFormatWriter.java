package com.myapplication.rick.packagedelivery;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Rick on 5-5-2016.
 */
class RouteFormatWriter {
    private Context context;

    public RouteFormatWriter(@NonNull Context context) {
        this.context = context;
    }
    public void writeStreets(RouteFormat routeFormat) throws IOException {
        //TODO: make file io work
        File folder = new File(Environment.getExternalStorageDirectory(), "PackageDelivery");
        File path = new File(folder, "myFile.csv");
        System.out.println(folder.toString());
        if (folder.mkdirs()) {
            System.out.println("Success");
        } else {
            System.out.println("failure");
        }
        System.out.println("Does folder exist: " + folder.exists());
        CSVWriter writer = new CSVWriter(new FileWriter(path));
        writer.writeAll(routeFormat.toCSVList());
        writer.close();
    }
}
