package com.app.conectar.model.forget_password_response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("userdata")
    val userdata: Userdata
)