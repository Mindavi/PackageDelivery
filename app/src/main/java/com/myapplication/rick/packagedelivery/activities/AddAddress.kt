package com.myapplication.rick.packagedelivery.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
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
                val streetNameAndSpace = "${street.name} "
                street_input.setText(streetNameAndSpace, TextView.BufferType.EDITABLE)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_address_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.add_address -> {
                val splitInput = street_input.text.toString().trim().split(' ')
                if (splitInput.isEmpty() || splitInput[0].isBlank()) {
                    Toast.makeText(this, "Please input a street name", Toast.LENGTH_SHORT).show()
                    street_input.setSelection(0)
                    return super.onOptionsItemSelected(item)
                }
                val number = splitInput.last().toIntOrNull() ?: run {
                    Toast.makeText(this, "Please input a number", Toast.LENGTH_SHORT).show()
                    street_input.setSelection(street_input.text.count())
                    return super.onOptionsItemSelected(item)
                }

                val streetName = splitInput.subList(0, splitInput.count() - 1).joinToString(" ")
                val street = routeFormat.route.find {
                    it.name.toUpperCase().contains(streetName.trim().toUpperCase()) &&
                    it.isValidNumber(number)
                } ?: run {
                    Toast.makeText(this, "Unknown street or invalid number", Toast.LENGTH_SHORT).show()
                    street_input.setSelection(street_input.text.count())
                    return super.onOptionsItemSelected(item)
                }
                sendResult(Address(street, number))
            }
        }
        return super.onOptionsItemSelected(item)
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
