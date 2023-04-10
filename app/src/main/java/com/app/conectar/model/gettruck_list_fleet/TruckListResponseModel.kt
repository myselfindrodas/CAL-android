package com.app.conectar.model.gettruck_list_fleet


import com.google.gson.annotations.SerializedName

data class TruckListResponseModel(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Status
)