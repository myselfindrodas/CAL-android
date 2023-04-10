package com.app.conectar.model.appintrolistresponse


import com.google.gson.annotations.SerializedName

data class AppIntro(
    @SerializedName("created_at")
    val createdAt: String, // 2023-03-02 05:49:07
    @SerializedName("deleted_at")
    val deletedAt: Any?, // null
    @SerializedName("description")
    val description: String, // new add
    @SerializedName("id")
    val id: String, // 1
    @SerializedName("image")
    val image: String, // https://developer.shyamfuture.in/itsc_calapp/uploads/intro_image/1677736147.jpg
    @SerializedName("status")
    val status: String, // 1
    @SerializedName("updated_at")
    val updatedAt: String // 2023-03-02 05:49:07
)