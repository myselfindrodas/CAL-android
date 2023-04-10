package com.app.conectar.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.app.conectar.R
import com.app.conectar.databinding.ActivityRegistrationHomeBinding
import com.app.conectar.session.SessionManager

class RegistrationHome : AppCompatActivity() {
    lateinit var activityRegistrationHomeBinding: ActivityRegistrationHomeBinding
    var sessionManager: SessionManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRegistrationHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration_home)
        sessionManager = SessionManager(this)


        activityRegistrationHomeBinding.btnIndependntTruckRegistration.setOnClickListener {

            val intent = Intent(this, RegistrationDetails::class.java)
            sessionManager?.setUser("IndependntTruckRegistration")
            startActivity(intent)

        }


        activityRegistrationHomeBinding.btnBack.setOnClickListener {

            onBackPressed()
        }



        activityRegistrationHomeBinding.btnMaterial.setOnClickListener {

            val intent = Intent(this, RegistrationDetails::class.java)
            sessionManager?.setUser("MaterialRegistration")
            startActivity(intent)


        }


        activityRegistrationHomeBinding.btnContractor.setOnClickListener {

            val intent = Intent(this, RegistrationDetails::class.java)
            sessionManager?.setUser("ContractorRegistration")
            startActivity(intent)
        }

    }
}