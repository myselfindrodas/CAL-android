package com.app.conectar.model.getdriver_list_fleet


import com.google.gson.annotations.SerializedName

data class Driver(
    @SerializedName("created_ts")
    val createdTs: String, // 2022-10-08 11:26:07
    @SerializedName("driver_certification_id")
    val driverCertificationId: Any?, // null
    @SerializedName("driver_id")
    val driverId: String, // 26
    @SerializedName("driver_licence")
    val driverLicence: String, // DFR123CD21
    @SerializedName("driver_licence_photo")
    val driverLicencePhoto: String, // driver_licence_photo80.jpg
    @SerializedName("driver_name")
    val driverName: String, // iOS Test Driver
    @SerializedName("driver_number")
    val driverNumber: String, // 9957099570
    @SerializedName("driver_photo")
    val driverPhoto: String, // driver_photo80.jpg
    @SerializedName("driverimage")
    val driverimage: String, // uploads/driver_image/driver_photo80.jpg
    @SerializedName("driverlicenceimage")
    val driverlicenceimage: String, // uploads/driver_image/driver_licence_photo80.jpg
    @SerializedName("email")
    val email: String, // iostestdriver@gmail.com
    @SerializedName("is_active")
    val isActive: String, // 1
    @SerializedName("updated_ts")
    val updatedTs: Any?, // null
    @SerializedName("user_id")
    val userId: String // 1840
)