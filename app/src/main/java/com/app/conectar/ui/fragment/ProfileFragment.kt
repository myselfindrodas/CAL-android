package com.app.conectar.ui.fragment

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.FragmentProfileBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.ui.activities.MainActivity
import com.app.conectar.utils.Status
import com.bumptech.glide.Glide


class ProfileFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentProfileBinding: FragmentProfileBinding

    private lateinit var viewModel: CommonViewModel
    var sessionManager: SessionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        fragmentProfileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        val root = fragmentProfileBinding.root
        mainActivity = activity as MainActivity
        sessionManager = SessionManager(mainActivity)

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        fragmentProfileBinding.btnBack.setOnClickListener {

            val intent = Intent(mainActivity, MainActivity::class.java)
            startActivity(intent)
        }

        sessionManager!!.getUserId()?.let { getProfile(it) }

        return root
    }

    private fun getProfile(userId: String) {


        if (CheckConnectivity.getInstance(mainActivity).isOnline) {

            viewModel.getUserProfileDetails(
                userId = userId
            ).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            mainActivity.hideProgressDialog()
                            resource.data?.result?.let { itProfileInfo ->

                                with(fragmentProfileBinding) {

                                    Glide.with(mainActivity)
                                        .load(itProfileInfo.profile.profileImage)
                                        .timeout(6000)
                                        .error(R.drawable.logo)
                                        .placeholder(R.drawable.logo)
                                        .into(PrfImg)

                                    tvUsername.text = itProfileInfo.profile.fullName
                                    tvUserRole.text =
                                        when (itProfileInfo.profile.userRole) {
                                            "2" -> {
                                                "Contractor"
                                            }
                                            "3" -> {
                                                "Material"
                                            }
                                            "4" -> {
                                                "Independnt"
                                            }
                                            "5" -> {
                                                "Independnt"
                                            }
                                            else -> {
                                                ""
                                            }
                                        }

                                    tvUserAddress.text = itProfileInfo.profile.address
                                    tvJobsCount.text = itProfileInfo.profile.totalJob
                                    tvRating.text = itProfileInfo.profile.avarageRating ?: "0"
                                    tvDaysMember.text = itProfileInfo.profile.daysAgo ?: "0"
                                    tvFirstname.text = itProfileInfo.profile.fname
                                    tvLastname.text = itProfileInfo.profile.lanme
                                    tvMobile.text = itProfileInfo.profile.mobile
                                    tvMobile.text = itProfileInfo.profile.mobile
                                    tvAddress.text = itProfileInfo.profile.address
                                    tvEmail.text = itProfileInfo.profile.email
                                    tvCountry.text = itProfileInfo.profile.country
                                    tvState.text = itProfileInfo.profile.state
                                    tvCity.text = itProfileInfo.profile.city
                                    tvDOB.text = itProfileInfo.profile.dob
                                    tvCompanyname.text = itProfileInfo.profile.companyName
                                    tvCompanydba.text = itProfileInfo.completeProfileInfo.companyDba
                                    tv2ndphone.text = itProfileInfo.completeProfileInfo.phone2
                                    tvCompanyein.text = itProfileInfo.completeProfileInfo.companyEin
                                    tvDLNo.text = itProfileInfo.completeProfileInfo.driverLicense
                                    tvDLExpireDate.text = itProfileInfo.completeProfileInfo.driverLicenseExp
                                    tvZipCode.text = itProfileInfo.completeProfileInfo.zip

                                }


                            }

                        }
                        Status.ERROR -> {
                            mainActivity.hideProgressDialog()
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
                            mainActivity.showProgressDialog()
                        }

                    }

                }
            }

        } else {

            Toast.makeText(mainActivity, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT)
                .show()

        }

    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val intent = Intent(mainActivity, MainActivity::class.java)
                    startActivity(intent)

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }



}