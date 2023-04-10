package com.app.conectar.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import java.lang.Exception

class CheckConnectivity {
    var connectivityManager: ConnectivityManager? = null
    var wifiInfo: NetworkInfo? = null
    var mobileInfo: NetworkInfo? = null
    var connected = false
    val isOnline: Boolean
        get() {
            try {
                connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager!!.activeNetworkInfo
                connected = networkInfo != null && networkInfo.isAvailable &&
                        networkInfo.isConnected
                return connected
            } catch (e: Exception) {
                println("CheckConnectivity Exception: " + e.message)
                Log.v("connectivity", e.toString())
            }
            return connected
        }

    companion object {
        var context: Context? = null

        private val instance = CheckConnectivity()
        fun getInstance(ctx: Context): CheckConnectivity {
            context = ctx.applicationContext
            return instance
        }
    }
}