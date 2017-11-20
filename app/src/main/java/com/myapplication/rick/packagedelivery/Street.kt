package com.myapplication.rick.packagedelivery

import java.util.Locale

/**
 * Created by Rick on 1-5-2016.
 */
internal class Street : CSVWriteAble {
    var name: String
        private set
    var range: Range
        private set
    private var direction: Direction

    @Throws(IllegalArgumentException::class)
    constructor(name: String, range: Range) {
        if (name.isEmpty()) {
            throw IllegalArgumentException("Empty name")
        }

        this.name = name
        this.range = range
        this.direction = Direction.LowToHigh //default value
    }

    @Throws(IllegalArgumentException::class)
    constructor(name: String, range: Range, direction: Direction) {
        if (name.isEmpty()) {
            throw IllegalArgumentException("Empty name")
        }

        this.name = name
        this.range = range
        this.direction = direction
    }

    override fun toString(): String {
        return "$name, $range"
        //return String.format(Locale.US, "%s, %s", name, range.toString())
    }

    override fun toCSV(): Array<String> {
        val values = ArrayList(range.toCSV().asList())
        values.add(0, name)

        //val values = arrayOfNulls<String>(1 + range.length())
        //values[0] = name
        //should be same as arraycopy
        //for (int i = 0; i < range.length(); i++) {
        //values[i + 1] = rangeValues[i];
        //}
        //System.arraycopy(rangeValues, 0, values, 1, rangeValues.size)
        return values.toTypedArray()
    }

    public fun getDirection(): Direction {
        return direction
    }
}
