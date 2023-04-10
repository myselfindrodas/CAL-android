package com.app.conectar.model.deletetruck


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("error_code")
    val errorCode: Int, // 0
    @SerializedName("message")
    val message: String // Truck Successfully Deleted.
)