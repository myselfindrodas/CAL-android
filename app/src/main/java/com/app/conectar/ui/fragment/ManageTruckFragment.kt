package com.app.conectar.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.FragmentManageTruckBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.ui.activities.MainActivity
import com.app.conectar.ui.adapter.TruckListAdapter
import com.app.conectar.utils.Status

class ManageTruckFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentManageTruckBinding: FragmentManageTruckBinding
    private lateinit var viewModel: CommonViewModel
    private lateinit var truckListAdapter: TruckListAdapter
    var sessionManager: SessionManager? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentManageTruckBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_manage_truck, container, false)
        val root = fragmentManageTruckBinding.root
        mainActivity = activity as MainActivity

        sessionManager = SessionManager(mainActivity)

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentManageTruckBinding.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }


        /*fragmentManageTruckBinding.layoutTrucklist.btnDetails.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_truckdetails)
        }*/

        setupRecyclewrView()
        sessionManager!!.getUserId()?.let { getTruckList(it) }


    }

    private fun setupRecyclewrView() {
        truckListAdapter = TruckListAdapter(requireContext())
        val mLayoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(mainActivity, 1)
        fragmentManageTruckBinding.rvTruckList.layoutManager = mLayoutManager
        //  fragmentManageDriverBinding.rvDriverList.addOnScrollListener(recyclerOnScroll)
        fragmentManageTruckBinding.rvTruckList.itemAnimator = DefaultItemAnimator()
        fragmentManageTruckBinding.rvTruckList.adapter = truckListAdapter
    }

    private fun getTruckList(userId: String) {


        if (CheckConnectivity.getInstance(mainActivity).isOnline) {

            viewModel.getTruckList(
                userId = userId
            ).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            mainActivity.hideProgressDialog()
                            resource.data?.result?.let { itProfileInfo ->


                                truckListAdapter.updateList(itProfileInfo.truckList)
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