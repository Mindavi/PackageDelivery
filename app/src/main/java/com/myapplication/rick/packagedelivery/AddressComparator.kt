package com.myapplication.rick.packagedelivery

/**
 * Created by Rick on 23-11-2017.
 */

internal class AddressComparator(private val routeFormat: RouteFormat): Comparator<Address>
{
    override fun compare(p0: Address?, p1: Address?): Int {
        if (p0 == null || p1 == null) {
            return if (p0 == null)
                -1
            else
                1

        }
        if (p0.street == p1.street)
        {
            return if (p0.street.direction == Direction.LowToHigh)
                p0.number - p1.number
            else
                p1.number - p0.number
        }
        return routeFormat.route.indexOf(p0.street) - routeFormat.route.indexOf(p1.street)
    }

}