package com.app.conectar.model.registrationresponse


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("userdata")
    val userdata: Userdata
)