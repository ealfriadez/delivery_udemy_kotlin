package com.pnp.kotlinudemydelivery.providers

import com.pnp.kotlinudemydelivery.api.ApiRoutes
import com.pnp.kotlinudemydelivery.models.ResponseHttp
import com.pnp.kotlinudemydelivery.models.User
import com.pnp.kotlinudemydelivery.routes.UsersRoutes
import retrofit2.Call

class UsersProviders {

    private var usersRoutes: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRoutes()
    }

    fun register(user: User): Call<ResponseHttp>?{
        return usersRoutes?.register(user)
    }

    fun login(email: String, password: String): Call<ResponseHttp>?{
        return usersRoutes?.login(email, password)
    }
}