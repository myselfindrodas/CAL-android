package com.app.conectar.model.gettruck_list_fleet


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("truck_list")
    val truckList: List<Truck>
)