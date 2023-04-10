package com.app.conectar.ui.fragment

import android.app.AlertDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.FragmentAlertsBinding
import com.app.conectar.databinding.FragmentJoblistBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.model.alertresponse.NotificationListModel
import com.app.conectar.model.newjobresponse.NewJobModel
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.ui.activities.MainActivity
import com.app.conectar.ui.adapter.AlertListAdapter
import com.app.conectar.ui.adapter.NewJobListAdapter
import com.app.conectar.utils.Status

class AlertsFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentAlertsBinding: FragmentAlertsBinding
    lateinit var alertListAdapter: AlertListAdapter
    private lateinit var viewModel: CommonViewModel
    var sessionManager: SessionManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentAlertsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_alerts,container,false)
        val root = fragmentAlertsBinding.root
        mainActivity = activity as MainActivity

        sessionManager = SessionManager(mainActivity)

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm


        setupRecyclewrView()
        NotificationList()

        fragmentAlertsBinding.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }

//        fragmentAlertsBinding.layoutalert.btnAlertsdetails.setOnClickListener {
//
//            val navController = Navigation.findNavController(it)
//            navController.navigate(R.id.nav_alertdetails)
//
//        }


        return root
    }

    private fun setupRecyclewrView() {
        alertListAdapter = AlertListAdapter(requireContext())
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(mainActivity, 1)
        fragmentAlertsBinding.rvAlrerts.layoutManager = mLayoutManager
        fragmentAlertsBinding.rvAlrerts.itemAnimator = DefaultItemAnimator()
        fragmentAlertsBinding.rvAlrerts.adapter = alertListAdapter
    }

    private fun NotificationList(){
        if (CheckConnectivity.getInstance(mainActivity).isOnline) {

            viewModel.notificationlist(user_master_id = sessionManager?.getUserId().toString()).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            mainActivity.hideProgressDialog()
                            resource.data?.result?.let { itProfileInfo ->


                                alertListAdapter.updateList(itProfileInfo.notificationList)
                                /* with(fragmentManageDriverBinding) {

                                     Glide.with(mainActivity)
                                         .load(itProfileInfo.profile.profileImage)
                                         .timeout(6000)
                                         .error(com.app.conectar.R.drawable.logo)
                                         .placeholder(com.app.conectar.R.drawable.logo)
                                         .into(PrfImg)


                                 }*/


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

}