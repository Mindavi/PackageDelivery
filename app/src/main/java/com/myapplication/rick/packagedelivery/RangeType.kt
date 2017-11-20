package com.myapplication.rick.packagedelivery

enum class RangeType {
    Even,
    Uneven,
    All;


    companion object {

        fun getRangeTypeFromNumber(number: Int): RangeType {
            return if (number % 2 == 0) {
                Even
            } else {
                Uneven
            }
        }
    }
}
