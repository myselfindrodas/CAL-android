package com.app.conectar.model.logoutresponse


import com.google.gson.annotations.SerializedName

data class LogoutModelResponse(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Status
)