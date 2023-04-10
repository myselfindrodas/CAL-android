package com.app.conectar.api.getProfileResponse


import com.google.gson.annotations.SerializedName

data class CompleteProfileInfo(
    @SerializedName("company_dba")
    val companyDba: String, // ATLLC
    @SerializedName("company_ein")
    val companyEin: String, // 123456789
    @SerializedName("company_legal_name")
    val companyLegalName: String, // Angel Trucks LLC
    @SerializedName("created_at")
    val createdAt: String, // 2022-10-06 21:58:01
    @SerializedName("dob")
    val dob: String, // 2000-01-01
    @SerializedName("driver_license")
    val driverLicense: String, // 987654321
    @SerializedName("driver_license_exp")
    val driverLicenseExp: String, // 2030-01-01
    @SerializedName("email_2")
    val email2: String, // 2nd@email.com
    @SerializedName("id")
    val id: String, // 29
    @SerializedName("is_billing_address")
    val isBillingAddress: String, // 0
    @SerializedName("phone_2")
    val phone2: String, // 5422466452
    @SerializedName("status")
    val status: String, // 1
    @SerializedName("street_address")
    val streetAddress: String, // 16303 elk glen st
    @SerializedName("updated_at")
    val updatedAt: String?, // null
    @SerializedName("user_master_id")
    val userMasterId: String, // 1838
    @SerializedName("zip")
    val zip: String // 78247
)