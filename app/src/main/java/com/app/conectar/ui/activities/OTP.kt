package com.app.conectar.ui.activities

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.ActivityOtpBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.utils.Status

class OTP : AppCompatActivity() {

    lateinit var activityOtpBinding: ActivityOtpBinding
    var otp: String? = ""
    var phone: String? = ""
    var validotp:String? = ""
    var sessionManager: SessionManager? = null
    private lateinit var viewModel: CommonViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityOtpBinding = DataBindingUtil.setContentView(this, R.layout.activity_otp)
        sessionManager = SessionManager(this)
        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm


        val intent = intent
        validotp = intent.getStringExtra("otp")
        phone = intent.getStringExtra("phone")
        Log.d(TAG, "masterid-->"+sessionManager?.getUserId())


        val index0 = 0
        val index1 = 1
        val index2 = 2
        val index3 = 3
        val otp1:String = validotp?.get(index0).toString()
        val otp2:String = validotp?.get(index1).toString()
        val otp3:String = validotp?.get(index2).toString()
        val otp4:String = validotp?.get(index3).toString()

        activityOtpBinding.otp1.setText(otp1)
        activityOtpBinding.otp2.setText(otp2)
        activityOtpBinding.otp3.setText(otp3)
        activityOtpBinding.otp4.setText(otp4)

        activityOtpBinding.tvSubtitle.text = "Enter the 4-digit code sent to you at "+phone


        val otptext = ArrayList<EditText>()
        otptext.add(activityOtpBinding.otp1)
        otptext.add(activityOtpBinding.otp2)
        otptext.add(activityOtpBinding.otp3)
        otptext.add(activityOtpBinding.otp4)

        setOtpEditTextHandler(otptext)


        activityOtpBinding.btnBack.setOnClickListener {

            onBackPressedDispatcher.onBackPressed()
        }


        activityOtpBinding.btnNext.setOnClickListener {
            otp =   activityOtpBinding.otp1.text.toString() +
                    activityOtpBinding.otp2.text.toString() +
                    activityOtpBinding.otp3.text.toString() +
                    activityOtpBinding.otp4.text.toString()

            if (otp?.length!! > 3) {

                verifyOtp()


            }else{
                Toast.makeText(this, "Enter OTP", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun setOtpEditTextHandler(otpEt: ArrayList<EditText>) { //This is the function to be called
        for (i in 0..3) { //Its designed for 6 digit com.app.conectar.activities.OTP
            otpEt.get(i).addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                }
                override fun afterTextChanged(s: Editable) {
                    if (i == 3 && otpEt[i].text.toString().isNotEmpty()) {

                    } else if (otpEt[i].text.toString().isNotEmpty()) {
                        otpEt[i + 1]
                            .requestFocus()
                    }
                }
            })
            otpEt[i].setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (event.action != KeyEvent.ACTION_DOWN) {
                    return@OnKeyListener false
                }
                if (keyCode == KeyEvent.KEYCODE_DEL &&
                    otpEt[i].text.toString().isEmpty() && i != 0
                ) {
                    otpEt[i - 1].setText("")
                    otpEt[i - 1].requestFocus()
                }
                false
            })
        }
    }

    private fun verifyOtp(){


        if (CheckConnectivity.getInstance(this).isOnline) {

            viewModel.verifyuser(
                user_master_id = sessionManager?.getUserId().toString(),
                otp = otp.toString()).observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            if (resource.data?.verifystatus?.error_code.equals("0")) {

                                sessionManager?.createLoginSession(sessionManager?.getUsername(), sessionManager?.getpassword())

                                Toast.makeText(this, resource.data?.verifystatus?.message, Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()

                            } else {

                                val builder = AlertDialog.Builder(this@OTP)
                                builder.setMessage(it.data?.verifystatus?.message)
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
                            Log.d(ContentValues.TAG, "print-->" + resource.data?.verifystatus)
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