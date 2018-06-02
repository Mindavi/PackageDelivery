package com.myapplication.rick.packagedelivery.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import com.myapplication.rick.packagedelivery.Address
import com.myapplication.rick.packagedelivery.R
import com.myapplication.rick.packagedelivery.RouteFormat
import com.myapplication.rick.packagedelivery.Street

import kotlinx.android.synthetic.main.activity_add_address.*

class AddAddress : AppCompatActivity() {

    private lateinit var routeFormat: RouteFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
        setSupportActionBar(toolbar)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        routeFormat = intent.getParcelableExtra(RouteCreation.INTENT_ROUTE_FORMAT)

//        val street = routeFormat.route[0]
//        val address = Address(street, street.range.lowerBound)
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, android.R.id.text1, routeFormat.route)
        suggestions_list.adapter = adapter

        // TODO: put street name in text box when clicked
        suggestions_list.setOnItemClickListener { adapterView, _, i, _ ->
            val street = adapterView.getItemAtPosition(i)
            Log.d("PackageDelivery", street.toString())
            if (street is Street) {
                street_input.setText(street.name, TextView.BufferType.EDITABLE)
                street_input.setSelection(street_input.text.length)
            }
        }

        street_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    internal companion object {
        const val CHOSEN_ADDRESS = "com.myapplication.rick.packagedelivery.address"
    }

    private fun sendResult(address: Address) {
        val intent = Intent()
        intent.putExtra(CHOSEN_ADDRESS, address)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
