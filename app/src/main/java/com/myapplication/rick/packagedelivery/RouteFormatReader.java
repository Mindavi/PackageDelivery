package com.myapplication.rick.packagedelivery;

import com.opencsv.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rick on 3-5-2016.
 */
class RouteFormatReader {
    public ArrayList<Street> parseStreets(String filename) throws IOException {
        ArrayList<Street> streets = new ArrayList<>();
            CSVReader reader = new CSVReader(new FileReader(filename));
            List<String[]> content = reader.readAll();

            for (String[] row : content) {
                if (row.length == 4) {
                    streets.add(
                            new Street(
                                    row[0],
                                    new Range(
                                            Integer.parseInt(row[1]),
                                            Integer.parseInt(row[2]),
                                            RangeType.valueOf(row[3])
                                    )));
                } else {
                    throw new IOException("File is not valid");
                }
            }
        return streets;
    }
}
