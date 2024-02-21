package com.pnp.kotlinudemydelivery.models

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("image") val image: String,
    @SerializedName("password") val password: String,
    @SerializedName("is_available") val isAvailable: Boolean,
    @SerializedName("session_token") val sessionToken: String
) {
    override fun toString(): String {
        return "User(id='$id', name='$name', lastname='$lastname', email='$email', phone='$phone', image='$image', password='$password', isAvailable=$isAvailable, sessionToken='$sessionToken')"
    }
}