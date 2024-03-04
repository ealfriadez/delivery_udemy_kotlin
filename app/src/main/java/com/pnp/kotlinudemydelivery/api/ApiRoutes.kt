package com.pnp.kotlinudemydelivery.api

import com.pnp.kotlinudemydelivery.routes.UsersRoutes

class ApiRoutes {

    val API_URL = "http://192.168.1.4:3000/api/"
    val retrofit = RetrofitClient()

    fun getUsersRoutes(): UsersRoutes{
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }
}