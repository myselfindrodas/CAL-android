package com.app.conectar.model.login.requestmodel

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @field:SerializedName("email")
    val email: String? = "",

    @field:SerializedName("password")
    val password: String? = "",

    @field:SerializedName("source")
    val source: String? = "",

    @field:SerializedName("device_token")
    val device_token: String? = "",

    @field:SerializedName("device_type")
    val device_type: String? = "",

)