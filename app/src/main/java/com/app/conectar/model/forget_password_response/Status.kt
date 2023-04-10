package com.app.conectar.model.forget_password_response


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("error_code")
    val errorCode: Int, // 0
    @SerializedName("message")
    val message: String // Password Token sent to your mail. Please use it in the time of update password.
)