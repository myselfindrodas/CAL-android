package com.app.conectar.model.truck_details_response


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("full_image")
    val fullImage: String // https://developer.shyamfuture.in/itsc_calapp/uploads/truck_image/
)