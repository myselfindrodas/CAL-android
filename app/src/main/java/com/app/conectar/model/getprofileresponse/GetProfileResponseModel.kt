package com.app.conectar.api.getProfileResponse


import com.google.gson.annotations.SerializedName

data class GetProfileResponseModel(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Status
)