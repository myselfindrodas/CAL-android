package com.app.conectar.model.truck_details_response


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("error_code")
    val errorCode: Int, // 0
    @SerializedName("message")
    val message: String // Truck Details.
)