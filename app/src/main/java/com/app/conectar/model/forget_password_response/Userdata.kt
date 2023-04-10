package com.app.conectar.model.forget_password_response


import com.google.gson.annotations.SerializedName

data class Userdata(
    @SerializedName("access_level")
    val accessLevel: String, // 2
    @SerializedName("address")
    val address: String, // 816 Camaron St, San Antonio, TX 78212, USA
    @SerializedName("cal_registration")
    val calRegistration: String, // abc
    @SerializedName("card_bank_id")
    val cardBankId: Any?, // null
    @SerializedName("city")
    val city: String,
    @SerializedName("company_name")
    val companyName: String, // AR Contractor LLC
    @SerializedName("company_registration")
    val companyRegistration: String, // 123456789
    @SerializedName("completed_steps")
    val completedSteps: String, // 3
    @SerializedName("country")
    val country: String,
    @SerializedName("created_ts")
    val createdTs: String, // 2022-10-06 22:16:47
    @SerializedName("dob")
    val dob: String, // 25-11-1969
    @SerializedName("email")
    val email: String, // arosa@itsc-tx.com
    @SerializedName("expiry_licencedate")
    val expiryLicencedate: String, // 16-05-2025
    @SerializedName("fname")
    val fname: String, // AR
    @SerializedName("is_active")
    val isActive: String, // 1
    @SerializedName("is_deleted")
    val isDeleted: String, // 0
    @SerializedName("lanme")
    val lanme: String, // Contractors
    @SerializedName("lat")
    val lat: String, // 29.4328853
    @SerializedName("license")
    val license: String,
    @SerializedName("license_number")
    val licenseNumber: String, // FRT784RT
    @SerializedName("login_status")
    val loginStatus: String, // 1
    @SerializedName("lon")
    val lon: String, // -98.5002949
    @SerializedName("mobile")
    val mobile: String, // 2104910009
    @SerializedName("numberof_truck")
    val numberofTruck: String, // 0
    @SerializedName("otp")
    val otp: String, // 4863
    @SerializedName("otp_time")
    val otpTime: String, // 2022-10-07 03:46:47
    @SerializedName("password")
    val password: String, // 0cef1fb10f60529028a71f58e54ed07b
    @SerializedName("password_token")
    val passwordToken: String, // 2549
    @SerializedName("payout_after_day")
    val payoutAfterDay: String, // 0
    @SerializedName("pincode")
    val pincode: String, // 78212
    @SerializedName("profile_image")
    val profileImage: String, // 1675670706.png
    @SerializedName("registration_source")
    val registrationSource: String, // MOB
    @SerializedName("sos_verification")
    val sosVerification: String, // 0
    @SerializedName("state")
    val state: String,
    @SerializedName("stripe_access_token")
    val stripeAccessToken: String,
    @SerializedName("stripe_account_id")
    val stripeAccountId: String,
    @SerializedName("stripe_publish_Key")
    val stripePublishKey: String,
    @SerializedName("stripe_refresh_token")
    val stripeRefreshToken: String,
    @SerializedName("stripe_token_type")
    val stripeTokenType: String,
    @SerializedName("stripeaccount_id")
    val stripeaccountId: Any?, // null
    @SerializedName("stripepayout_id")
    val stripepayoutId: Any?, // null
    @SerializedName("updated_ts")
    val updatedTs: String, // 0
    @SerializedName("user_master_id")
    val userMasterId: String, // 1839
    @SerializedName("user_role")
    val userRole: String, // 2
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("vcerification_device")
    val vcerificationDevice: String, // d3c034d5c693110d24ab4c39d38ac78f719f5ad2afe269b43f789fc0ccb93dff
    @SerializedName("verification_code")
    val verificationCode: String, // 9297
    @SerializedName("verification_status")
    val verificationStatus: String, // 0
    @SerializedName("verification_time")
    val verificationTime: String // 2023-02-07 03:13:43
)