package com.myapplication.rick.packagedelivery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button_click(View view) {
        ArrayList<Street> streets = new ArrayList<>();
        streets.add(new Street("trentstraat", new Range(1, 40, RangeType.All)));
        streets.add(new Street("Leekbusweg", new Range(1, 15, RangeType.All)));
        streets.add(new Street("teststraat", new Range(1, 89, RangeType.Uneven)));

        RouteFormat routeFormat = new RouteFormat(streets);
        RouteFormatWriter routeFormatWriter = new RouteFormatWriter(this);
        try {
            routeFormatWriter.writeStreets(routeFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
