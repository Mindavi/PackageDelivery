package com.myapplication.rick.packagedelivery

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable

class StreetAdapter(context: Context, resourceId: Int, resourceId2: Int, private val list: ArrayList<Street>): ArrayAdapter<Street>(context, resourceId, resourceId2, list), Filterable {
    private var filteredData: ArrayList<Street> = list
    override fun getItem(position: Int): Street {
        return filteredData.get(position)
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
                for (i in list.indices) {
                    if (list[i].name.toUpperCase()
                                    .contains(constraint.toString().toUpperCase())) {
                        filterList.add(list[i])
                    }
                }
                results.count = filterList.count()
                results.values = filterList

                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredData = results?.values as ArrayList<Street>
                notifyDataSetChanged()
            }
        }
    }
}
