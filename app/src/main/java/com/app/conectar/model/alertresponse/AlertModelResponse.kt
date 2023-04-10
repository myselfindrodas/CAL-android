package com.app.conectar.model.alertresponse


import com.google.gson.annotations.SerializedName

data class AlertModelResponse(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Status
)