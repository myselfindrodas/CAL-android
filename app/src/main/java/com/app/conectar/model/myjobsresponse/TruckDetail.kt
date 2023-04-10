package com.app.conectar.model.myjobsresponse


import com.google.gson.annotations.SerializedName

data class TruckDetail(
    @SerializedName("empty_truck_slip")
    val emptyTruckSlip: String, // https://developer.shyamfuture.in/itsc_calapp/uploads/truck_image/
    @SerializedName("empty_truck_weight")
    val emptyTruckWeight: String,
    @SerializedName("loaded_truck_slip")
    val loadedTruckSlip: String, // https://developer.shyamfuture.in/itsc_calapp/uploads/truck_image/
    @SerializedName("loaded_truck_weight")
    val loadedTruckWeight: String,
    @SerializedName("truck_id")
    val truckId: String, // 0
    @SerializedName("truck_reg_number")
    val truckRegNumber: String? // UK10F1562
)