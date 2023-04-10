package com.app.conectar.utils

import android.text.TextUtils
import android.util.Patterns


object Constant {


    fun isValidEmail(email: String): Boolean {
        return  Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /*fun phoneValidation(context: Context, phNo:String, code:String) :Boolean{
        val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.createInstance(context)
        return try {
            val swissNumberProto = phoneUtil.parse(phNo, code)
            phoneUtil.isPossibleNumber(swissNumberProto);
        } catch (e: NumberParseException) {
            System.err.println("NumberParseException was thrown: $e")
            false
        }
    }*/
}