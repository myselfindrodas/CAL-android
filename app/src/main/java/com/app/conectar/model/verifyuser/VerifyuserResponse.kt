package com.app.conectar.model.verifyuser

import com.app.conectar.model.login.responsemodel.status
import com.google.gson.annotations.SerializedName

class VerifyuserResponse(
    @field:SerializedName("status")
    val verifystatus: verifystatus? = null

    )


data class verifystatus(


    @field:SerializedName("error_code")
    val error_code: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    )