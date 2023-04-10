package com.app.conectar.ui.activities

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
import com.app.conectar.databinding.ActivityDriverjobAddressBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.utils.Status

class DriverjobAddress : AppCompatActivity() {

    lateinit var activityDriverjobAddressBinding: ActivityDriverjobAddressBinding

    var token: String? = ""
    var sessionManager: SessionManager? = null
    private lateinit var viewModel: CommonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDriverjobAddressBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_driverjob_address
        )
        sessionManager = SessionManager(this)
        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        val intent = intent
        token = intent.getStringExtra("token")

        with(activityDriverjobAddressBinding) {

            etToken.setText(token)

            topnav.btnBack.setOnClickListener {

                onBackPressedDispatcher.onBackPressed()
            }

            llContinue.setOnClickListener {
                if (validate()) {
                    getPasswordValidation(etPassword.text.toString(), etToken.text.toString())

                }
            }
        }
    }


    private fun getPasswordValidation(password: String, password_token: String) {


        if (CheckConnectivity.getInstance(this).isOnline) {

            viewModel.getPasswordResetUpdate(
                password = password,
                password_token = password_token
            ).observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            if (resource.data?.status?.errorCode == 0) {

                                Toast.makeText(
                                    this,
                                    resource.data.status.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(
                                    Intent(
                                        this@DriverjobAddress,
                                        LoginemailActivity::class.java
                                    )
                                )
                                finishAffinity()

                            }
                        }
                        Status.ERROR -> {
                            hideProgressDialog()
                            Log.d(ContentValues.TAG, "print-->" + resource.data?.status)

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

            Toast.makeText(this, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT)
                .show()

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

    private fun validate(): Boolean {
        return if (activityDriverjobAddressBinding.etToken.text.isEmpty()) {
            Toast.makeText(this, "Enter your token", Toast.LENGTH_SHORT).show()
            activityDriverjobAddressBinding.etToken.requestFocus()
            false
        } else if (activityDriverjobAddressBinding.etPassword.text.isEmpty() || activityDriverjobAddressBinding.etConfPassword.text.isEmpty()) {
            Toast.makeText(this, "Enter password and confirm password", Toast.LENGTH_SHORT).show()
            activityDriverjobAddressBinding.etConfPassword.requestFocus()
            false
        } else if (activityDriverjobAddressBinding.etPassword.text.toString() != activityDriverjobAddressBinding.etConfPassword.text.toString()) {
            Toast.makeText(this, "Password and Confirm Password is not same", Toast.LENGTH_SHORT)
                .show()
            activityDriverjobAddressBinding.etConfPassword.requestFocus()
            false
        } else
            true
    }
}