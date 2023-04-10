package com.app.conectar.model.edittruck_details


import com.google.gson.annotations.SerializedName

data class EditTruckDetailsRequestModel(
    @SerializedName("truck_id")
    var truck_id: String,
    @SerializedName("user_master_id")
    var user_master_id: String,
    @SerializedName("truck_vin_number")
    var truck_vin_number: String,
    @SerializedName("truck_reg_number")
    var truck_reg_number: String,
    @SerializedName("truck_make")
    var truck_make: String,
    @SerializedName("truck_body_style")
    var truck_body_style: String,
    @SerializedName("truck_model")
    var truck_model: String,
    @SerializedName("truck_color")
    var truck_color: String,
    @SerializedName("truck_cabin_type")
    var truck_cabin_type: String,
    @SerializedName("truck_empty_weight")
    var truck_empty_weight: String,
    @SerializedName("truck_carring_capacity")
    var truck_carring_capacity: String,
    @SerializedName("truck_weight_metric")
    var truck_weight_metric: String,
    @SerializedName("truck_type")
    var truck_type: String,
    @SerializedName("company_trucknumber")
    var company_trucknumber: String,
)

