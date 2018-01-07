package com.myapplication.rick.packagedelivery

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.routecreation_address_recyclerview.view.*

/**
 * Created by Rick on 6-1-2018.
 */
class AddressAdapter(private val addresses: ArrayList<Address>): RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.routecreation_address_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = addresses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val address = addresses[position]
        holder.textViewStreetName.text = address.street.name
        holder.textViewStreetNumber.text = address.number.toString()
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val textViewStreetName: TextView = view.street_name_text
        val textViewStreetNumber : TextView = view.street_number_text
    }
}