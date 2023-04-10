package com.app.conectar.model.alertresponse


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("alert_type")
    val alertType: String, // 3
    @SerializedName("notification_count")
    val notificationCount: NotificationCount,
    @SerializedName("notification_list")
    val notificationList: List<Notification>,
    @SerializedName("stripe_check")
    val stripeCheck: Any? // null
)