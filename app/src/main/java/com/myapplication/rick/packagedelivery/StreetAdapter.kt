package com.myapplication.rick.packagedelivery

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable

class StreetAdapter(context: Context, resourceId: Int, resourceId2: Int, private val list: ArrayList<Street>): ArrayAdapter<Street>(context, resourceId, resourceId2, list), Filterable {
    private var filteredData: ArrayList<Street> = list
    override fun getItem(position: Int): Street {
        return filteredData[position]
    }

    override fun getCount(): Int {
        return filteredData.count()
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()

                Log.d("StreetAdapter", "filtering")

                if (constraint == null) return results
                val filterList = ArrayList<Street>()
                if (!constraint.toString().trim().isBlank()) {

                    for (i in list.indices) {
                        val splitInput = constraint.toString().trim().split(' ')

                        val number = splitInput.last().toIntOrNull()
                        var streetName: String
                        var isValidNumberOrHasNoNumber: Boolean
                        if (number == null) {
                            streetName = constraint.toString().trim()
                            isValidNumberOrHasNoNumber = true
                        } else {
                            streetName = splitInput.subList(0, splitInput.count() - 1).joinToString(" ")
                            isValidNumberOrHasNoNumber = list[i].isValidNumber(number)
                        }
                        val containsStreetName = list[i].name.toUpperCase()
                                .contains(streetName.trim().toUpperCase())
                        if (containsStreetName && isValidNumberOrHasNoNumber) {
                            filterList.add(list[i])
                        }
                    }
                }
                else {
                    filterList.addAll(list)
                }
                results.count = filterList.count()
                results.values = filterList

                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredData = results?.values as ArrayList<Street>
                notifyDataSetChanged()
            }
        }
    }
}
