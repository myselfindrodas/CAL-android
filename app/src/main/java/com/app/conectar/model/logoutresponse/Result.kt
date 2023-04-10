package com.app.conectar.model.logoutresponse


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("userdata")
    val userdata: Userdata
)