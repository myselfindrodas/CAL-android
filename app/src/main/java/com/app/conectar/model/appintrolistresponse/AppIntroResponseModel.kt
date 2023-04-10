package com.app.conectar.model.appintrolistresponse


import com.google.gson.annotations.SerializedName

data class AppIntroResponseModel(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Status
)