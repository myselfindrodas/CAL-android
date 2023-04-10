package com.app.conectar.model.citylistresponse

import com.app.conectar.model.statelistresponse.State
import com.app.conectar.model.statelistresponse.stateresult
import com.app.conectar.model.statelistresponse.statestatus
import com.google.gson.annotations.SerializedName

class CityListResponseModel(

    @field:SerializedName("status")
    val citystatus: citystatus? = null,

    @field:SerializedName("result")
    val cityresult: cityresult? = null,
)


data class citystatus(
    @SerializedName("error_code")
    val errorCode: Int, // 0
    @SerializedName("message")
    val message: String // 1 Results.
)


data class cityresult(
    @SerializedName("data")
    val citydatalist: List<City>
)


data class City(

    @SerializedName("id")
    val id: String,
    @SerializedName("city_name")
    val city_name: String
)


