package com.app.conectar.model.myjobsresponse


import com.google.gson.annotations.SerializedName

data class MyJobResponseModel(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Status
)