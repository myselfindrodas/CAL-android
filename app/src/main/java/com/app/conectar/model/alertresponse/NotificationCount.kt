package com.app.conectar.model.alertresponse


import com.google.gson.annotations.SerializedName

data class NotificationCount(
    @SerializedName("count")
    val count: String // 1
)