package com.pnp.kotlinudemydelivery.api

import com.pnp.kotlinudemydelivery.routes.UsersRoutes

class ApiRoutes {

    val API_URL = "http://172.31.2.216:3000/api/"
    val retrofit = RetrofitClient()

    fun getUsersRoutes(): UsersRoutes{
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }
}