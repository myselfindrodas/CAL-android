package com.app.conectar.model.forget_password_response

import com.google.gson.annotations.SerializedName

class ForgotPasswordResponseModel(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Status
)