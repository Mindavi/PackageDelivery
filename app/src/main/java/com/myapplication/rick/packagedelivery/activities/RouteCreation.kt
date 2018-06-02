package com.myapplication.rick.packagedelivery.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_route_creation.*
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(resultCode) {
            Activity.RESULT_OK -> {
                val address = data?.getParcelableExtra<Address>(AddAddress.CHOSEN_ADDRESS) ?: return
                route.addAddress(address)
                addresses.add(address)
                routeAdapter.notifyDataSetChanged()
            }
            Activity.RESULT_CANCELED -> {
                // nothing to do
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.add_street -> {
                val intent = newIntent(this, route.routeFormat)
                startActivityForResult(intent, REQUEST_ADDRESS)
            }
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(INTENT_ROUTE, route)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_creation)
        val toolbar = creation_toolbar
        setSupportActionBar(toolbar)

        route = if (null != savedInstanceState) {
            savedInstanceState.getParcelable(INTENT_ROUTE)
        } else {
            val routeFormat = intent.getParcelableExtra<RouteFormat>(RouteSelection.INTENT_ROUTE_FORMAT)
            routeFormat.route.forEach { println(it.name) }
            Route(routeFormat)
        }

        route_creation_recycler_view.setHasFixedSize(true)
        routeLayoutManager = LinearLayoutManager(this)
        route_creation_recycler_view.layoutManager = routeLayoutManager
        routeAdapter = AddressAdapter(addresses)
        route_creation_recycler_view.adapter = routeAdapter
    }

    internal companion object {
        const val INTENT_ROUTE_FORMAT = "com.myapplication.rick.packagedelivery.routeFormat"
        const val INTENT_ROUTE = "com.myapplication.rick.packagedelivery.route"
        const val REQUEST_ADDRESS = 1000

        private fun newIntent(context: Context, routeFormat: RouteFormat?): Intent {
            val intent = Intent(context, AddAddress::class.java)
            if (routeFormat != null) intent.putExtra(INTENT_ROUTE_FORMAT, routeFormat)
            return intent
        }
    }
}
