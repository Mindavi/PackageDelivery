package com.myapplication.rick.packagedelivery.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import com.myapplication.rick.packagedelivery.*

import kotlinx.android.synthetic.main.activity_add_address.*

class AddAddress : AppCompatActivity() {

    private lateinit var routeFormat: RouteFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
        setSupportActionBar(toolbar)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // TODO: make the back button work

        routeFormat = intent.getParcelableExtra(RouteCreation.INTENT_ROUTE_FORMAT)

        val adapter = StreetAdapter(this, R.layout.simple_list_item_1, android.R.id.text1, routeFormat.route)
        suggestions_list.adapter = adapter

        suggestions_list.setOnItemClickListener { adapterView, _, i, _ ->
            val street = adapterView.getItemAtPosition(i)
            Log.d("PackageDelivery", street.toString())
            if (street is Street) {
                street_input.setText(street.name, TextView.BufferType.EDITABLE)
                street_input.setSelection(street_input.text.length)
            }
        }

        clear_button.setOnClickListener { _ ->
            street_input.setText("")
        }

        street_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                adapter.filter.filter(street_input.text)
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
