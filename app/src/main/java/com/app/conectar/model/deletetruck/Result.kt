package com.app.conectar.model.deletetruck


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("truck_details")
    val truckDetails: TruckDetails
)