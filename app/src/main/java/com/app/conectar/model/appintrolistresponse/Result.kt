package com.app.conectar.model.appintrolistresponse


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("AppIntro")
    val appIntro: List<AppIntro>
)