package com.app.conectar.ui.activities

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.ContentValues
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
import com.app.conectar.databinding.ActivityLoginemailBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.utils.Constant
import com.app.conectar.utils.Status


class LoginemailActivity : AppCompatActivity() {

    lateinit var activityLoginemailBinding: ActivityLoginemailBinding
    var sessionManager: SessionManager? = null
    private lateinit var viewModel: CommonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginemailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_loginemail)
        sessionManager = SessionManager(this)
        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        activityLoginemailBinding.btnForgotpassword.setOnClickListener {

            startActivity(Intent(this, Forgotpassword::class.java))
        }

        activityLoginemailBinding.btnLogin.setOnClickListener {

            if (validate())
                login(
                    activityLoginemailBinding.etEmail.text.toString(),
                    activityLoginemailBinding.etPass.text.toString()
                )
        }


        activityLoginemailBinding.btnBack.setOnClickListener {

            onBackPressedDispatcher.onBackPressed()
        }


    }


    private fun validate(): Boolean {
        if (activityLoginemailBinding.etEmail.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show()
            activityLoginemailBinding.etEmail.requestFocus()
            return false
        } else if (!Constant.isValidEmail(activityLoginemailBinding.etEmail.text.toString())) {
            Toast.makeText(this, "Enter valid Email Id", Toast.LENGTH_SHORT).show()
            activityLoginemailBinding.etEmail.requestFocus()
            return false
        } else if (activityLoginemailBinding.etPass.text.isEmpty() || activityLoginemailBinding.etPass.text.length <= 7) {
            Toast.makeText(
                this,
                "The password must be at least 8 characters.",
                Toast.LENGTH_SHORT
            ).show()
            activityLoginemailBinding.etPass.requestFocus()
            return false
        } else
            return true
    }


    private fun login(username: String, password: String) {


        if (CheckConnectivity.getInstance(this).isOnline) {

            viewModel.login(
                email = username,
                password = password,
                source = "MOB",
                device_token = "dlS8OxpKTGy3otoaJga6xM:APA91bEGzzNR2io9o5eViBxp-kHVyPW32RXYhmz8IchQ8S0rCPT2LGw4Z8ptULVYnWcp_8mRXmSVYYZf5Dc2JAcaN8g7VNL5_GiSM4flYVQorG_djtAcL6RHvxzzVg427Wp8yMKVZzk7",
                device_type = "2"
            ).observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            Log.d(ContentValues.TAG, "errorcode-->" + resource.data?.status?.error_code)
                            if (resource.data?.status?.error_code==0) {


                                sessionManager?.createLoginSession(
                                    activityLoginemailBinding.etPass.text.toString(),
                                    activityLoginemailBinding.etEmail.text.toString()
                                )

                                resource.data.result?.userdata?.user_master_id.let { userId ->
                                    sessionManager?.setUserId(userId)
                                }

                                val builder = AlertDialog.Builder(this@LoginemailActivity)
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

                                    val intent = Intent(this, MainActivity::class.java)
                                    sessionManager?.setUser(resgistrationtype)
                                    startActivity(intent)
                                    finish()
                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.show()
                            } else {

                                val builder = AlertDialog.Builder(this@LoginemailActivity)
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
                            Log.d(ContentValues.TAG, "print-->" + resource.data?.status?.error_code)

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

}