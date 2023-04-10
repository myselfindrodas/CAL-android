package com.app.conectar.model.getdriver_list_fleet


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("error_code")
    val errorCode: Int, // 0
    @SerializedName("message")
    val message: String // Driver List.
)