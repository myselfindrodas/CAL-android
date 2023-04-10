package com.app.conectar.model.truck_details_response


import com.google.gson.annotations.SerializedName

data class TruckDetails(
    @SerializedName("company_trucknumber")
    val companyTrucknumber: String, // 5656
    @SerializedName("created_ts")
    val createdTs: String, // 2023-02-20 13:57:25
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("is_active")
    val isActive: String, // 1
    @SerializedName("truck_body_style")
    val truckBodyStyle: String, // test
    @SerializedName("truck_cabin_type")
    val truckCabinType: String, // test
    @SerializedName("truck_carring_capacity")
    val truckCarringCapacity: String, // 222
    @SerializedName("truck_color")
    val truckColor: String, // black
    @SerializedName("truck_empty_weight")
    val truckEmptyWeight: String, // 111
    @SerializedName("truck_id")
    val truckId: String, // 73
    @SerializedName("truck_image")
    val truckImage: String,
    @SerializedName("truck_make")
    val truckMake: String, // test
    @SerializedName("truck_model")
    val truckModel: String, // test
    @SerializedName("truck_reg_number")
    val truckRegNumber: String, // 64677668
    @SerializedName("truck_type")
    val truckType: String, // 17
    @SerializedName("truck_type_name")
    val truckTypeName: String, // Side Dump Truck (5 axles) (25 tons)
    @SerializedName("truck_vin_number")
    val truckVinNumber: String, // 854565545644
    @SerializedName("truck_weight_metric")
    val truckWeightMetric: String, // 333
    @SerializedName("truckimage")
    val truckimage: String, // uploads/truck_image/
    @SerializedName("updated_ts")
    val updatedTs: String, // 2023-02-20 14:03:42
    @SerializedName("user_id")
    val userId: String // 1923
)