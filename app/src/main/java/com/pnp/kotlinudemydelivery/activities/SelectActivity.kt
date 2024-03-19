package com.pnp.kotlinudemydelivery.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.pnp.kotlinudemydelivery.R
import com.pnp.kotlinudemydelivery.adapters.RolesAdapter
import com.pnp.kotlinudemydelivery.models.User
import com.pnp.kotlinudemydelivery.utils.SharedPref

class SelectActivity : AppCompatActivity() {

    var recyclerViewRoles: RecyclerView? = null
    var user: User? = null
    var adapter: RolesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        recyclerViewRoles = findViewById(R.id.recyclerview_roles)
        recyclerViewRoles?.layoutManager = LinearLayoutManager(this)

        getUserFromSession()

        adapter = RolesAdapter(this, user?.roles!!)
        recyclerViewRoles?.adapter = adapter
    }

    private fun getUserFromSession(){

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()){
            //SI EL USUARIO EXISTE EN SESION
            user = gson.fromJson(sharedPref.getData("user"), User:: class.java)
        }
    }
}