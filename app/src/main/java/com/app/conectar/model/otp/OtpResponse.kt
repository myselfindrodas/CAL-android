package com.app.conectar.model.otp

import com.app.conectar.model.login.responsemodel.status
import com.google.gson.annotations.SerializedName

data class OtpResponse(

    @field:SerializedName("status")
    val otpstatus: otpstatus? = null,

    @field:SerializedName("result")
    val otpresult: otpresult? = null,

    )


data class otpstatus(


    @field:SerializedName("error_code")
    val error_code: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    )


data class otpresult(

    @field:SerializedName("otp")
    val otp: String? = null,

    )

