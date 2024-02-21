package com.pnp.kotlinudemydelivery.routes

import com.pnp.kotlinudemydelivery.models.ResponseHttp
import com.pnp.kotlinudemydelivery.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersRoutes {

    @POST("users/create")
    fun register(@Body user: User): Call<ResponseHttp>
}