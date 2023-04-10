package com.app.conectar.model.truck_details_response


import com.google.gson.annotations.SerializedName

data class TruckDetailsResponseModel(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Status
)