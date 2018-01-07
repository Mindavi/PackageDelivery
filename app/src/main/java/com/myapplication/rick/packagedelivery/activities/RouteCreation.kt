package com.myapplication.rick.packagedelivery.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_route_creation.*
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.myapplication.rick.packagedelivery.*


class RouteCreation : AppCompatActivity() {
    private lateinit var route: Route
    private lateinit var routeAdapter: AddressAdapter
    private lateinit var routeLayoutManager: RecyclerView.LayoutManager
    private val addresses: ArrayList<Address> = ArrayList()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.creation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.add_street -> {
                Toast.makeText(this, "Text", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_creation)
        val toolbar = creation_toolbar
        setSupportActionBar(toolbar)
        val routeFormat = intent.getParcelableExtra<RouteFormat>(RouteSelection.INTENT_ROUTE_FORMAT)
        routeFormat.route.forEach { println(it.name) }
        route = Route(routeFormat)

        route_creation_recycler_view.setHasFixedSize(true)
        routeLayoutManager = LinearLayoutManager(this)
        route_creation_recycler_view.layoutManager = routeLayoutManager
        routeAdapter = AddressAdapter(addresses)
        route_creation_recycler_view.adapter = routeAdapter

        addresses.add(Address(Street("Street1", Range(1, 30, RangeType.All)), 15))
    }
}
