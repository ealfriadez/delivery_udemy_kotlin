package com.pnp.kotlinudemydelivery.activities.client.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.pnp.kotlinudemydelivery.R
import com.pnp.kotlinudemydelivery.models.User
import com.pnp.kotlinudemydelivery.utils.SharedPref

class ClientHomeActivity : AppCompatActivity() {

    private val TAG = "ClientHomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)

        getUserFromSession()
    }

    private fun getUserFromSession(){

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()){
            //SI EL USUARIO EXISTE EN SESION
            val user = gson.fromJson(sharedPref.getData("user"), User:: class.java)
            //Log.d(TAG, "Usuario: $user")
            Log.d("ClientHomeActivity", "Usuario: $user")
        }
    }
}