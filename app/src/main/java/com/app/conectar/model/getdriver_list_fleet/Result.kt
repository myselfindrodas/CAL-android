package com.app.conectar.model.getdriver_list_fleet


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("driver_list")
    val driverList: List<Driver>
)