package com.app.conectar.model.myjobsresponse


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("jobs")
    val jobs: List<Job>
)