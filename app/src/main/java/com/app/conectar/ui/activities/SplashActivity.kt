package com.app.conectar.ui.activities

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.app.conectar.R
import com.app.conectar.databinding.ActivitySplashBinding
import com.app.conectar.session.SessionManager
import com.app.conectar.utils.PrefManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions

class SplashActivity : AppCompatActivity() {

    lateinit var activitySplashBinding: ActivitySplashBinding
    var path:String?=null
    private var prefManager: PrefManager? = null
    var sessionManager: SessionManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        sessionManager = SessionManager(this)
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        prefManager = PrefManager(this)
        path =  "android.resource://" + getPackageName() + "/" + R.raw.connectarvideo
        activitySplashBinding.vdSplash.setVideoURI(Uri.parse(path))
        activitySplashBinding.vdSplash.start()
        val secondsDelayed = 1
        Handler().postDelayed({



            requestAllPermission()


        }, (secondsDelayed * 5000).toLong())


    }





    private fun requestAllPermission() {
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)
        val rationale = "This app needs permission to use app feature. You need to grant them for using the application."
        val options: Permissions.Options = Permissions.Options()
            .setRationaleDialogTitle("Info")
            .setSettingsDialogTitle("Warning")

        Permissions.check(this, permissions, rationale, options,
            object : PermissionHandler() {
                override fun onGranted() {

                    Toast.makeText(applicationContext, "All permissions are granted!", Toast.LENGTH_SHORT).show()
                    val secondsDelayed = 1
                    Handler().postDelayed({
                        if (prefManager!!.isFirstTimeLaunch) {
                            val intent = Intent(this@SplashActivity, Welcome::class.java)
                            startActivity(intent)
                            finish()
                        }else{

                            if (sessionManager!!.isLoggedIn) {

                                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()

                            }else{
                                val intent = Intent(this@SplashActivity, LoginphoneActivity::class.java)
                                startActivity(intent)
                                finish()
                            }

                        }
                    }, (secondsDelayed * 2000).toLong())
                }

                override fun onDenied(context: Context?, deniedPermissions: ArrayList<String?>?) {

                    finishAndRemoveTask()
                }
            })

    }



}