package com.app.conectar.session

import android.content.Context
import android.content.SharedPreferences
import android.content.Intent
import com.app.conectar.ui.activities.LoginphoneActivity

class SessionManager(  // Context
    var _context: Context
) {
    // Shared Preferences
    var pref: SharedPreferences

    // Editor for Shared preferences
    var editor: SharedPreferences.Editor

    // Shared pref mode
    var PRIVATE_MODE = 0
    var TOKEN_PREFERENCES = "TokenPreferences"
    var USER_TOKEN = "UserToken"



    /**
     * Create login session
     */
    fun createLoginSession(username: String?, password: String?) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_username, username)
        editor.putString(KEY_password, password)
        editor.commit()
    }

    fun checkLogin() {
        // Check login status
        if (!isLoggedIn) {
            // user is not logged in redirect him to Login Activity
            val i = Intent(_context, Long::class.java)
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            // Add new Flag to start new Activity
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            // Staring Login Activity
            _context.startActivity(i)
        }
    }

    fun setToken(token: String?){
        pref.edit().putString("token", token).commit()
    }



    fun getToken(): String?{
        return  pref.getString("token", "")
    }


    fun setUser(user: String?){
        pref.edit().putString("user", user).commit()
    }



    fun getUser(): String?{
        return  pref.getString("user", "")
    }



    fun setUsername(username: String?){
        pref.edit().putString("username", username).commit()
    }



    fun getFirstname(): String?{
        return  pref.getString("firstname", "")
    }


    fun setFirstname(firstname: String?){
        pref.edit().putString("firstname", firstname).commit()
    }



    fun getLastname(): String?{
        return  pref.getString("lastname", "")
    }


    fun setLastname(lastname: String?){
        pref.edit().putString("lastname", lastname).commit()
    }




    fun getMobile(): String?{
        return  pref.getString("mobile", "")
    }


    fun setMobile(mobile: String?){
        pref.edit().putString("mobile", mobile).commit()
    }




    fun getUsername(): String?{
        return  pref.getString("username", "")
    }



    fun setPassword(password: String?){
        pref.edit().putString("password", password).commit()
    }



    fun getpassword(): String?{
        return  pref.getString("password", "")
    }






    fun setUserId(user: String?){
        pref.edit().putString(KEY_userId, user).commit()
    }

    fun getUserId(): String?{
        return  pref.getString(KEY_userId, "")
    }


    /**
     * Clear session details
     */
    fun logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear()
        editor.commit()

        // After logout redirect user to Loing Activity
        val i = Intent(_context, LoginphoneActivity::class.java)
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // Add new Flag to start new Activity
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        _context.startActivity(i)
    }

    // Get Login State
    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)

    companion object {
        private const val PREF_NAME = "NWS_Pref"
        private const val IS_LOGIN = "IsLoggedIn"

        //username
        const val KEY_username = "username"

        //password
        const val KEY_password = "password"
        const val KEY_batchcount = "batchcount"
        const val KEY_VENDOR = "key_vandor"
        const val KEY_ADDRESS = "key_address"
        const val KEY_userId = "userid"
    }

    // Constructor
    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }
}