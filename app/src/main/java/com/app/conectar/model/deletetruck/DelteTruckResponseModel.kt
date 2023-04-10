package com.app.conectar.model.deletetruck


import com.google.gson.annotations.SerializedName

data class DelteTruckResponseModel(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Status
)