package com.app.conectar.model.statelistresponse

import com.google.gson.annotations.SerializedName

class StateListResponseModel(
    @field:SerializedName("status")
    val statestatus: statestatus? = null,

    @field:SerializedName("result")
    val stateresult: stateresult? = null,
)

data class statestatus(
    @SerializedName("error_code")
    val errorCode: Int, // 0
    @SerializedName("message")
    val message: String // 1 Results.
)

data class stateresult(
    @SerializedName("data")
    val datalist: List<State>
)


data class State(

    @SerializedName("state_id")
    val state_id: String,
    @SerializedName("state_name")
    val state_name: String, // 2023-01-16 00:36:41
    @SerializedName("country_id")
    val country_id: String, // 1
    @SerializedName("status")
    val status: String
)