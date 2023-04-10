package com.app.conectar.api.getProfileResponse


import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("access_level")
    val accessLevel: String, // 2
    @SerializedName("address")
    val address: String, // 16303 Elk Glen St, San Antonio, TX 78247, USA
    @SerializedName("avarage_rating")
    val avarageRating: String?, // null
    @SerializedName("cal_registration")
    val calRegistration: String, // abc
    @SerializedName("card_bank_id")
    val cardBankId: String, // ba_1LzfwWRUMmnqvxewafUZvbhS
    @SerializedName("city")
    val city: String, // San Antonio
    @SerializedName("company_name")
    val companyName: String, // AR Independent LLC
    @SerializedName("company_registration")
    val companyRegistration: String, // DFGI45JN
    @SerializedName("completed_steps")
    val completedSteps: String, // 6
    @SerializedName("country")
    val country: String, // United States
    @SerializedName("created_ts")
    val createdTs: String, // 2022-10-06 21:54:01
    @SerializedName("days_ago")
    val daysAgo: String, // 132 Days
    @SerializedName("dob")
    val dob: String, // 23-10-2018
    @SerializedName("email")
    val email: String, // afrosa10@gmail.com
    @SerializedName("expiry_licencedate")
    val expiryLicencedate: String, // 15-03-2023
    @SerializedName("fname")
    val fname: String, // Angel
    @SerializedName("full_name")
    val fullName: String, // Angel Rosa
    @SerializedName("is_active")
    val isActive: String, // 1
    @SerializedName("is_deleted")
    val isDeleted: String, // 0
    @SerializedName("lanme")
    val lanme: String, // Rosa
    @SerializedName("lat")
    val lat: String, // 29.5902963
    @SerializedName("license")
    val license: String,
    @SerializedName("license_number")
    val licenseNumber: String,
    @SerializedName("login_status")
    val loginStatus: String, // 1
    @SerializedName("lon")
    val lon: String, // -98.3727951
    @SerializedName("mobile")
    val mobile: String, // 2103231624
    @SerializedName("numberof_truck")
    val numberofTruck: String, // 0
    @SerializedName("otp")
    val otp: String, // 3946
    @SerializedName("otp_time")
    val otpTime: String, // 2022-10-07 03:24:01
    @SerializedName("password")
    val password: String, // 1e28284f59e926547bb6793ad8723722
    @SerializedName("password_token")
    val passwordToken: String,
    @SerializedName("payout_after_day")
    val payoutAfterDay: String, // 0
    @SerializedName("pincode")
    val pincode: String, // 78247
    @SerializedName("profile_image")
    val profileImage: String, // https://developer.shyamfuture.in/itsc_calapp/uploads/profile_image/t5Zki3cn.jpg
    @SerializedName("registration_source")
    val registrationSource: String, // MOB
    @SerializedName("sos_verification")
    val sosVerification: String, // 1
    @SerializedName("state")
    val state: String, // TX
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
    val stripeaccountId: String, // acct_1Lq2BnRUMmnqvxew
    @SerializedName("stripepayout_id")
    val stripepayoutId: String, // acct_1Lq2BnRUMmnqvxew
    @SerializedName("total_job")
    val totalJob: String, // 13
    @SerializedName("updated_ts")
    val updatedTs: String, // 0
    @SerializedName("user_master_id")
    val userMasterId: String, // 1838
    @SerializedName("user_role")
    val userRole: String, // 4
    @SerializedName("user_type")
    val userType: String, // I
    @SerializedName("vcerification_device")
    val vcerificationDevice: String, // ba4739e301e94372e354dd1687b2c9e0d6822cb548bd9e9cfdecc909b94c0ef6
    @SerializedName("verification_code")
    val verificationCode: String, // 6879
    @SerializedName("verification_status")
    val verificationStatus: String, // 0
    @SerializedName("verification_time")
    val verificationTime: String // 2023-02-07 09:56:55
)