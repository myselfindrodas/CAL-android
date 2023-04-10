package com.app.conectar.model.registrationresponse


import com.google.gson.annotations.SerializedName

data class Userdata(
    @SerializedName("access_level")
    val accessLevel: String, // 2
    @SerializedName("address")
    val address: String,
    @SerializedName("cal_registration")
    val calRegistration: String,
    @SerializedName("card_bank_id")
    val cardBankId: Any?, // null
    @SerializedName("city")
    val city: String, // 18461
    @SerializedName("city_name")
    val cityName: String, // Ailey
    @SerializedName("company_dba")
    val companyDba: Any?, // null
    @SerializedName("company_ein")
    val companyEin: Any?, // null
    @SerializedName("company_legal_name")
    val companyLegalName: Any?, // null
    @SerializedName("company_name")
    val companyName: String, // Shyam Steel
    @SerializedName("company_registration")
    val companyRegistration: String,
    @SerializedName("completed_steps")
    val completedSteps: String, // 1
    @SerializedName("country")
    val country: String,
    @SerializedName("created_ts")
    val createdTs: String, // 2023-02-28 06:52:09
    @SerializedName("dob")
    val dob: String,
    @SerializedName("driver_license")
    val driverLicense: Any?, // null
    @SerializedName("driver_license_exp")
    val driverLicenseExp: Any?, // null
    @SerializedName("email")
    val email: String, // indro11@yopmail.com
    @SerializedName("email_2")
    val email2: Any?, // null
    @SerializedName("expiry_licencedate")
    val expiryLicencedate: String,
    @SerializedName("fname")
    val fname: String, // Indrajit
    @SerializedName("is_active")
    val isActive: String, // 1
    @SerializedName("is_deleted")
    val isDeleted: String, // 0
    @SerializedName("lanme")
    val lanme: String, // Das
    @SerializedName("lat")
    val lat: String, // 0
    @SerializedName("license")
    val license: String,
    @SerializedName("license_number")
    val licenseNumber: String,
    @SerializedName("login_status")
    val loginStatus: String, // 0
    @SerializedName("lon")
    val lon: String, // 0
    @SerializedName("mobile")
    val mobile: String, // 8013845072
    @SerializedName("numberof_truck")
    val numberofTruck: String, // 0
    @SerializedName("otp")
    val otp: String, // 1327
    @SerializedName("otp_time")
    val otpTime: String, // 2023-02-28 06:52:09
    @SerializedName("password")
    val password: String, // 25d55ad283aa400af464c76d713c07ad
    @SerializedName("password_token")
    val passwordToken: String,
    @SerializedName("payout_after_day")
    val payoutAfterDay: String, // 0
    @SerializedName("phone_2")
    val phone2: Any?, // null
    @SerializedName("pincode")
    val pincode: String,
    @SerializedName("profile_image")
    val profileImage: String, // https://conectar.cc/assets/no-img.png
    @SerializedName("registration_source")
    val registrationSource: String, // MOB
    @SerializedName("sos_verification")
    val sosVerification: String, // 0
    @SerializedName("state")
    val state: String, // 3931
    @SerializedName("state_name")
    val stateName: String, // Georgia
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
    val userMasterId: String, // 1925
    @SerializedName("user_role")
    val userRole: String, // 2
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("vcerification_device")
    val vcerificationDevice: String,
    @SerializedName("verification_code")
    val verificationCode: String, // 0
    @SerializedName("verification_status")
    val verificationStatus: String, // 0
    @SerializedName("verification_time")
    val verificationTime: String // 0000-00-00 00:00:00
)