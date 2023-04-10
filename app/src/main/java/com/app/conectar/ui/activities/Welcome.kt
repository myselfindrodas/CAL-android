package com.app.conectar.ui.activities

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.ActivityWelcomeBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.ui.adapter.InfoGraphicListAdapter
import com.app.conectar.utils.PrefManager
import com.app.conectar.utils.Status

class Welcome : AppCompatActivity() {
    lateinit var activityWelcomeBinding: ActivityWelcomeBinding
    private var prefManager: PrefManager? = null
    private lateinit var viewModel: CommonViewModel
    lateinit var infoGraphicListAdapter: InfoGraphicListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefManager = PrefManager(this)
        if (!prefManager!!.isFirstTimeLaunch) {
            launchHomeScreen()
            finish()
        }
        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
        activityWelcomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        activityWelcomeBinding.btnNext.setOnClickListener {

            launchHomeScreen()

        }

        setupRecyclewrView()
        getinfographiclist()

    }


    private fun setupRecyclewrView() {
        infoGraphicListAdapter = InfoGraphicListAdapter(this)
        activityWelcomeBinding.rvInfographic.setAdapter(infoGraphicListAdapter)
        activityWelcomeBinding.dotsIndicator.attachTo(activityWelcomeBinding.rvInfographic)
    }


    private fun getinfographiclist() {

        if (CheckConnectivity.getInstance(this).isOnline) {

            viewModel.getappIntroList()
                .observe(this) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                hideProgressDialog()
                                resource.data?.result?.let { itProfileInfo ->


                                    infoGraphicListAdapter.updateList(itProfileInfo.appIntro)

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

            Toast.makeText(this, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT).show()

        }


    }


    private fun launchHomeScreen() {
        prefManager!!.isFirstTimeLaunch = false
        startActivity(Intent(this@Welcome, LoginphoneActivity::class.java))
        finish()
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