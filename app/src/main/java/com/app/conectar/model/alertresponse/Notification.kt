package com.app.conectar.model.alertresponse


import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("admin_read")
    val adminRead: String, // 1
    @SerializedName("alert_type")
    val alertType: String, // 3
    @SerializedName("created_ts")
    val createdTs: String, // 2021-11-18 15:19:10
    @SerializedName("is_active")
    val isActive: String, // 1
    @SerializedName("msg_type")
    val msgType: String, // 2
    @SerializedName("notification_description")
    val notificationDescription: String, // Profile is Successfully Created in CAL
    @SerializedName("notification_id")
    val notificationId: String, // 4
    @SerializedName("notification_title")
    val notificationTitle: String, // Welcome To CAL
    @SerializedName("updated_ts")
    val updatedTs: String, // 2022-03-02 12:53:24
    @SerializedName("user_id")
    val userId: String, // 341
    @SerializedName("view_status")
    val viewStatus: String // 0
)