package com.app.conectar.model.truck_details_response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("truck_details")
    val truckDetails: TruckDetails
)