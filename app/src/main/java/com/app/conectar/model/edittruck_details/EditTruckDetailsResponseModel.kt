package com.app.conectar.model.edittruck_details


import com.google.gson.annotations.SerializedName

data class EditTruckDetailsResponseModel(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Status
)