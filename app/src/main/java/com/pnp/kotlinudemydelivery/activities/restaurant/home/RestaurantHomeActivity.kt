package com.pnp.kotlinudemydelivery.activities.restaurant.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.pnp.kotlinudemydelivery.R
import com.pnp.kotlinudemydelivery.activities.MainActivity
import com.pnp.kotlinudemydelivery.fragments.client.ClientCAtegoriesFragment
import com.pnp.kotlinudemydelivery.fragments.client.ClientOrdersFragment
import com.pnp.kotlinudemydelivery.fragments.client.ClientProfileFragment
import com.pnp.kotlinudemydelivery.fragments.restaurant.RestaurantCategoryFragment
import com.pnp.kotlinudemydelivery.fragments.restaurant.RestaurantOrderFragment
import com.pnp.kotlinudemydelivery.fragments.restaurant.RestaurantProductFragment
import com.pnp.kotlinudemydelivery.models.User
import com.pnp.kotlinudemydelivery.utils.SharedPref

class RestaurantHomeActivity: AppCompatActivity() {

    private val TAG = "RestaurantHomeActivity"
    var sharedPref: SharedPref? = null
    var bottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_home)
        sharedPref = SharedPref(this)

        openFragment(RestaurantOrderFragment())

        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation?.setOnItemSelectedListener {

            when(it.itemId){
                R.id.item_home -> {
                    openFragment(RestaurantOrderFragment())
                    true
                }

                R.id.item_category -> {
                    openFragment(RestaurantCategoryFragment())
                    true
                }

                R.id.item_product -> {
                    openFragment(RestaurantProductFragment())
                    true
                }

                R.id.item_profile -> {
                    openFragment(ClientProfileFragment())
                    true
                }

                else -> false
            }
        }

        getUserFromSession()
    }

    private fun openFragment(fragment: Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun logout(){
        sharedPref?.remove("user")
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun getUserFromSession(){

        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            //SI EL USUARIO EXISTE EN SESION
            val user = gson.fromJson(sharedPref?.getData("user"), User:: class.java)
            //Log.d(TAG, "Usuario: $user")
            Log.d("RestaurantHomeActivity", "Usuario: $user")
        }
    }
}