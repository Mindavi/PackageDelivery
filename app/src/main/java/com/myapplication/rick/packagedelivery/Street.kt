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
    }

    override fun toCSV(): Array<String> {
        val values = ArrayList(range.toCSV().asList())
        values.add(0, name)
        values.add(direction.toString())
        return values.toTypedArray()
    }

    fun getDirection(): Direction {
        return direction
    }
}
