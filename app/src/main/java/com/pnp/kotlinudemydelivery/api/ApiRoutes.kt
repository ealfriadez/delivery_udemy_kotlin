package com.pnp.kotlinudemydelivery.api

import com.pnp.kotlinudemydelivery.routes.UsersRoutes
import java.net.Inet4Address
import java.net.NetworkInterface

class ApiRoutes {

    val API_URL = "http://172.31.2.216:3000/api/"
    val retrofit = RetrofitClient()

    fun getUsersRoutes(): UsersRoutes{
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }

    fun getIPHostAddress(): String {
        NetworkInterface.getNetworkInterfaces()?.toList()?.map { networkInterface ->
            networkInterface.inetAddresses?.toList()?.find {
                !it.isLoopbackAddress && it is Inet4Address
            }?.let { return it.hostAddress }
        }
        return ""
    }
}