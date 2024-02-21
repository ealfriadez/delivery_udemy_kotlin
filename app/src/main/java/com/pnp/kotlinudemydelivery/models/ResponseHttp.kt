package com.pnp.kotlinudemydelivery.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class ResponseHttp (
    @SerializedName("success") val isSuccess: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: JsonObject,
    @SerializedName("error") val error: String
){

    override fun toString(): String {
        return "RsponseHttp(isSuccess='$isSuccess', message='$message', data=$data, error='$error')"
    }
}