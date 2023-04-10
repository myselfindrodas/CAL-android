package com.app.conectar.ui.activities

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.ActivityLoginBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.utils.Status

class LoginphoneActivity : AppCompatActivity() {
    lateinit var activityLoginBinding: ActivityLoginBinding
    var sessionManager: SessionManager? = null
    private lateinit var viewModel: CommonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        sessionManager = SessionManager(this)
        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm


        activityLoginBinding.btnLoginemail.setOnClickListener {

            startActivity(Intent(this, LoginemailActivity::class.java))

        }

        activityLoginBinding.btnDriverjob.setOnClickListener {

            startActivity(Intent(this, DriverjobAddress::class.java))

        }

        activityLoginBinding.btnRegstrationHome.setOnClickListener {

            startActivity(Intent(this, RegistrationHome::class.java))

        }


        activityLoginBinding.btnNext.setOnClickListener {

            if (validate())

                loginwithphone(
                    activityLoginBinding.ccp.selectedCountryCodeWithPlus.toString(),
                    activityLoginBinding.etPhone.text.toString()
                )
//                startActivity(Intent(this, OTP::class.java))

        }


    }


    private fun validate(): Boolean {
        return if (activityLoginBinding.etPhone.text.isEmpty() || activityLoginBinding.etPhone.text.length < 7) {
            Toast.makeText(this, "Enter a valid Phone Number", Toast.LENGTH_SHORT).show()
            activityLoginBinding.etPhone.requestFocus()
            false
        } else
            true
    }


    private fun loginwithphone(countrycode: String, mobile: String) {

        if (CheckConnectivity.getInstance(this).isOnline) {

            viewModel.loginwithphone(
                country_code = countrycode,
                mobile = mobile,
                source = "MOB",
                device_token = "dlS8OxpKTGy3otoaJga6xM:APA91bEGzzNR2io9o5eViBxp-kHVyPW32RXYhmz8IchQ8S0rCPT2LGw4Z8ptULVYnWcp_8mRXmSVYYZf5Dc2JAcaN8g7VNL5_GiSM4flYVQorG_djtAcL6RHvxzzVg427Wp8yMKVZzk7",
                device_type = "2"
            ).observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            Log.d(TAG, "errorcode-->" + resource.data?.status?.error_code)
                            if (resource.data?.status?.error_code==0) {


//                                sessionManager?.createLoginSession(countrycode, mobile)

                                resource.data.result?.userdata?.user_master_id.let { userId ->
                                    sessionManager?.setUserId(userId)
                                }

                                val builder = AlertDialog.Builder(this@LoginphoneActivity)
                                builder.setMessage(resource.data.status.message)
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->
                                    var resgistrationtype: String? = ""
                                    when {
                                        resource.data.result?.userdata?.user_role.equals("2") -> {
                                            resgistrationtype = "ContractorRegistration"
                                        }
                                        resource.data.result?.userdata?.user_role.equals("3") -> {
                                            resgistrationtype = "MaterialRegistration"
                                        }
                                        resource.data.result?.userdata?.user_role.equals("4") -> {
                                            resgistrationtype = "IndependntTruckRegistration"
                                        }
                                        resource.data.result?.userdata?.user_role.equals("5") -> {
                                            resgistrationtype = "FleetRegistration"
                                        }
                                    }

                                    sessionManager?.setUsername(resource.data.result?.userdata?.email)
                                    sessionManager?.setPassword(resource.data.result?.userdata?.password)
                                    sessionManager?.setFirstname(resource.data.result?.userdata?.fname)
                                    sessionManager?.setLastname(resource.data.result?.userdata?.lanme)
                                    sessionManager?.setMobile(resource.data.result?.userdata?.mobile)

                                    sessionManager?.setUser(resgistrationtype)
                                    resendotp(
                                        resource.data.result?.userdata?.user_master_id.toString(),
                                        resource.data.result?.userdata?.email.toString(),
                                        resource.data.result?.userdata?.mobile.toString()
                                    )

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.show()
                            } else if (resource.data?.status?.error_code==1) {

                                val intent = Intent(this, Mapview::class.java)
                                startActivity(intent)
                                finish()


                            } else {

                                val builder = AlertDialog.Builder(this@LoginphoneActivity)
                                builder.setMessage(it.data?.status?.message)
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.show()

                            }


                        }
                        Status.ERROR -> {
                            hideProgressDialog()
                            Log.d(ContentValues.TAG, "print-->" + resource.data?.status)
                            val intent = Intent(this, Mapview::class.java)
                            startActivity(intent)
                            finish()
//                            if (it.message!!.contains("401", true)) {
//                                val builder = AlertDialog.Builder(this@LoginemailActivity)
//                                builder.setMessage("Invalid Employee Id / Password")
//                                builder.setPositiveButton(
//                                    "Ok"
//                                ) { dialog, which ->
//
//                                    dialog.cancel()
//
//                                }
//                                val alert = builder.create()
//                                alert.show()
//                            } else
//                                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                        }

                        Status.LOADING -> {
                            showProgressDialog()
                        }

                    }

                }
            }

        } else {

            Toast.makeText(this, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT).show()

        }


    }


    private fun resendotp(masterid: String, email: String, mobile: String) {

        if (CheckConnectivity.getInstance(this).isOnline) {

            viewModel.resendotp(
                user_master_id = masterid,
                email = email,
                mobile = mobile,
            ).observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            if (resource.data?.otpstatus?.error_code.equals("0")) {


                                val intent = Intent(this, OTP::class.java)
                                intent.putExtra("otp", resource.data?.otpresult?.otp)
                                intent.putExtra("phone", mobile)
                                startActivity(intent)
                                finish()

                            } else {

                                val builder = AlertDialog.Builder(this@LoginphoneActivity)
                                builder.setMessage(it.data?.otpstatus?.message)
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.show()

                            }


                        }
                        Status.ERROR -> {
                            hideProgressDialog()
                            Log.d(ContentValues.TAG, "print-->" + resource.data?.otpstatus)
//                            if (it.message!!.contains("401", true)) {
//                                val builder = AlertDialog.Builder(this@LoginemailActivity)
//                                builder.setMessage("Invalid Employee Id / Password")
//                                builder.setPositiveButton(
//                                    "Ok"
//                                ) { dialog, which ->
//
//                                    dialog.cancel()
//
//                                }
//                                val alert = builder.create()
//                                alert.show()
//                            } else
//                                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                        }

                        Status.LOADING -> {
                            showProgressDialog()
                        }

                    }

                }
            }

        } else {

            Toast.makeText(this, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT).show()

        }


    }


    var mProgressDialog: ProgressDialog? = null

    fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.setMessage("Loading...")
            mProgressDialog!!.isIndeterminate = true
        }
        mProgressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}