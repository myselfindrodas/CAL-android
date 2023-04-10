package com.app.conectar.model.getdriver_list_fleet


import com.google.gson.annotations.SerializedName

data class DriverListResponseModel(
        @SerializedName("result")
        val result: Result,
        @SerializedName("status")
        val status: Status
)