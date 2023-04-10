package com.app.conectar.model.registrationresponse


import com.google.gson.annotations.SerializedName

data class RegistrationResponseModel(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Status
)