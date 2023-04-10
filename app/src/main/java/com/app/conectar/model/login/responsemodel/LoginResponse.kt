package com.app.conectar.model.login.responsemodel

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("status")
    val status: status? = null,

    @field:SerializedName("result")
    val result: result? = null,
)

data class status(


    @field:SerializedName("error_code")
    val error_code: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,

    )

data class result(

    @field:SerializedName("userdata")
    val userdata: userdata? = null,

    )

data class userdata(

    @field:SerializedName("user_master_id")
    val user_master_id: String? = null,

    @field:SerializedName("user_role")
    val user_role: String? = null,

    @field:SerializedName("access_level")
    val access_level: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("password_token")
    val password_token: String? = null,

    @field:SerializedName("registration_source")
    val registration_source: String? = null,

    @field:SerializedName("fname")
    val fname: String? = null,

    @field:SerializedName("lanme")
    val lanme: String? = null,

    @field:SerializedName("mobile")
    val mobile: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("state")
    val state: String? = null,

    @field:SerializedName("pincode")
    val pincode: String? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("lat")
    val lat: String? = null,

    @field:SerializedName("lon")
    val lon: String? = null,

    @field:SerializedName("dob")
    val dob: String? = null,

    @field:SerializedName("expiry_licencedate")
    val expiry_licencedate: String? = null,

    @field:SerializedName("cal_registration")
    val cal_registration: String? = null,


    @field:SerializedName("profile_image")
    val profile_image: String? = null,

    @field:SerializedName("license_number")
    val license_number: String? = null,

    @field:SerializedName("company_name")
    val company_name: String? = null,

    @field:SerializedName("company_registration")
    val company_registration: String? = null,

    @field:SerializedName("license")
    val license: String? = null,

    @field:SerializedName("login_status")
    val login_status: String? = null,



    @field:SerializedName("otp")
    val otp: String? = null,

    @field:SerializedName("otp_time")
    val otp_time: String? = null,

    @field:SerializedName("verification_code")
    val verification_code: String? = null,

    @field:SerializedName("vcerification_device")
    val vcerification_device: String? = null,

    @field:SerializedName("verification_status")
    val verification_status: String? = null,

    @field:SerializedName("user_type")
    val user_type: String? = null,



    @field:SerializedName("numberof_truck")
    val numberof_truck: String? = null,

    @field:SerializedName("sos_verification")
    val sos_verification: String? = null,

    @field:SerializedName("stripeaccount_id")
    val stripeaccount_id: String? = null,

    @field:SerializedName("stripepayout_id")
    val stripepayout_id: String? = null,

    @field:SerializedName("card_bank_id")
    val card_bank_id: String? = null,

    @field:SerializedName("stripe_account_id")
    val stripe_account_id: String? = null,


    @field:SerializedName("stripe_publish_Key")
    val stripe_publish_Key: String? = null,

    @field:SerializedName("stripe_token_type")
    val stripe_token_type: String? = null,

    @field:SerializedName("stripe_refresh_token")
    val stripe_refresh_token: String? = null,

    @field:SerializedName("stripe_access_token")
    val stripe_access_token: String? = null,

    @field:SerializedName("payout_after_day")
    val payout_after_day: String? = null,

    @field:SerializedName("completed_steps")
    val completed_steps: String? = null,


    @field:SerializedName("is_active")
    val is_active: String? = null,

    @field:SerializedName("is_deleted")
    val is_deleted: String? = null,

    @field:SerializedName("updated_ts")
    val updated_ts: String? = null,

    @field:SerializedName("is_trusted")
    val is_trusted: String? = null,

    @field:SerializedName("city_name")
    val city_name: String? = null,

    @field:SerializedName("state_name")
    val state_name: String? = null,


//    @field:SerializedName("bank_details")
//    val bank_details: bankdetails? = null,

    )


//data class bankdetails(
//
//    @field:SerializedName("id")
//    val id: String? = null,
//
//    @field:SerializedName("user_master_id")
//    val user_master_id: String? = null,
//
//    @field:SerializedName("connect_type")
//    val connect_type: String? = null,
//
//
//    @field:SerializedName("name")
//    val name: String? = null,
//
//    @field:SerializedName("account")
//    val account: String? = null,
//
//    @field:SerializedName("account_type")
//    val account_type: String? = null,
//
//    @field:SerializedName("routing")
//    val routing: String? = null,
//
//    @field:SerializedName("card_no")
//    val card_no: String? = null,
//
//    @field:SerializedName("is_default")
//    val is_default: String? = null,
//
//    @field:SerializedName("response")
//    val response: String? = null,
//
//    @field:SerializedName("status")
//    val status: String? = null,
//
//)