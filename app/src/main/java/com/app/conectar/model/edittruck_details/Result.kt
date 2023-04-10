package com.app.conectar.model.edittruck_details


import com.app.conectar.model.forget_password_response.Userdata
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("userdata")
    val userdata: Userdata
)