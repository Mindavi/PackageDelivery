package com.myapplication.rick.packagedelivery.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.myapplication.rick.packagedelivery.Address
import com.myapplication.rick.packagedelivery.R
import com.myapplication.rick.packagedelivery.RouteFormat

import kotlinx.android.synthetic.main.activity_add_address.*

class AddAddress : AppCompatActivity() {

    private lateinit var routeFormat: RouteFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        routeFormat = intent.getParcelableExtra(RouteCreation.INTENT_ROUTE_FORMAT)

        val street = routeFormat.route[0]
        val address = Address(street, street.range.lowerBound)

        val intent = Intent()
        intent.putExtra(CHOSEN_ADDRESS, address)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    internal companion object {
        const val CHOSEN_ADDRESS = "com.myapplication.rick.packagedelivery.address"
    }
}
