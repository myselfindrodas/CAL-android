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
import androidx.fragment.app.viewModels
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.ActivityForgotpasswordBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.utils.Constant
import com.app.conectar.utils.Status
import com.bumptech.glide.Glide

class Forgotpassword : AppCompatActivity() {

    lateinit var activityForgotpasswordBinding: ActivityForgotpasswordBinding
    private lateinit var viewModel: CommonViewModel
    var sessionManager: SessionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityForgotpasswordBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_forgotpassword)

        sessionManager = SessionManager(this)

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        with(activityForgotpasswordBinding){

            btnBack.setOnClickListener {

                onBackPressedDispatcher.onBackPressed()
            }
            btnLogin.setOnClickListener {
                if (validate()){
                    getForgetPasswordResponse(activityForgotpasswordBinding.etEmail.text.toString().trim())
                }
            }
        }
    }

    private fun getForgetPasswordResponse(emailId: String) {


        if (CheckConnectivity.getInstance(this).isOnline) {

            viewModel.getForgetPassword(
                emailId = emailId
            ).observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            if (resource.data?.status?.errorCode==0) {
                                resource.data.result.let { itData ->

                                    println("TOKEN ${itData.userdata.passwordToken}")
                                    val intent = Intent(this, DriverjobAddress::class.java)
                                    intent.putExtra("token", itData.userdata.passwordToken)
                                    // intent.putExtra("phone", mobile)

                                    startActivity(intent)

                                }
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

    private fun validate(): Boolean {
        if (activityForgotpasswordBinding.etEmail.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show()
            activityForgotpasswordBinding.etEmail.requestFocus()
            return false
        } else if (!Constant.isValidEmail(activityForgotpasswordBinding.etEmail.text.toString())) {
            Toast.makeText(this, "Enter valid Email Id", Toast.LENGTH_SHORT).show()
            activityForgotpasswordBinding.etEmail.requestFocus()
            return false
        } else return true
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