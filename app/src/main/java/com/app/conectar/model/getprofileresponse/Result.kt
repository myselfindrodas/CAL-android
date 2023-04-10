package com.app.conectar.api.getProfileResponse


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("billing_info")
    val billingInfo: String?, // null
    @SerializedName("complete_profile_info")
    val completeProfileInfo: CompleteProfileInfo,
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("profile")
    val profile: Profile
)