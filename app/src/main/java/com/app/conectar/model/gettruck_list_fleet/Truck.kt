package com.app.conectar.model.gettruck_list_fleet


import com.google.gson.annotations.SerializedName

data class Truck(
    @SerializedName("company_trucknumber")
    val companyTrucknumber: String,
    @SerializedName("created_ts")
    val createdTs: String, // 2023-01-16 00:36:41
    @SerializedName("is_active")
    val isActive: String, // 1
    @SerializedName("truck_body_style")
    val truckBodyStyle: String, // jsnsjd
    @SerializedName("truck_cabin_type")
    val truckCabinType: String, // jandjdd
    @SerializedName("truck_carring_capacity")
    val truckCarringCapacity: String, // 65000
    @SerializedName("truck_color")
    val truckColor: String, // jsnsjdd
    @SerializedName("truck_empty_weight")
    val truckEmptyWeight: String, // 55000
    @SerializedName("truck_id")
    val truckId: String, // 70
    @SerializedName("truck_image")
    val truckImage: String,
    @SerializedName("truck_make")
    val truckMake: String, // jsjdkd
    @SerializedName("truck_model")
    val truckModel: String, // jsnskf
    @SerializedName("truck_reg_number")
    val truckRegNumber: String, // 838282737
    @SerializedName("truck_type")
    val truckType: String, // 6
    @SerializedName("truck_type_name")
    val truckTypeName: String, // Dump Truck with Pup (5 axles) (22 tons)
    @SerializedName("truck_vin_number")
    val truckVinNumber: String, // 6494644
    @SerializedName("truck_weight_metric")
    val truckWeightMetric: String, // 4
    @SerializedName("truckimage")
    val truckimage: String, // uploads/truck_image/
    @SerializedName("updated_ts")
    val updatedTs: Any?, // null
    @SerializedName("user_id")
    val userId: String // 1826
)