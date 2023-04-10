package com.app.conectar.ui.fragment

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.FragmentTruckdetailsBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.model.truck_details_response.TruckDetails
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.ui.activities.MainActivity
import com.app.conectar.ui.activities.OTP
import com.app.conectar.utils.Status
import com.bumptech.glide.Glide

class TruckdetailsFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentTruckdetailsBinding: FragmentTruckdetailsBinding
    private lateinit var viewModel: CommonViewModel
    var sessionManager: SessionManager? = null
    var truck_id = ""
    var truck_type_id = ""
    lateinit var truckDetails: TruckDetails


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTruckdetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_truckdetails, container, false)
        val root = fragmentTruckdetailsBinding.root

        mainActivity = activity as MainActivity
        sessionManager = SessionManager(mainActivity)

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm
        val intent = arguments
        truck_id = intent!!.getString("truck_id")!!
        truck_type_id = intent.getString("truck_type_id")!!


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentTruckdetailsBinding.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }


        fragmentTruckdetailsBinding.btnDeletetruck.setOnClickListener {


            val builder = AlertDialog.Builder(mainActivity)
            builder.setMessage("Do you really want to delete?")
            builder.setPositiveButton(
                "yes"
            ) { dialog, which ->

                deleteTruck(truck_id, it)
                dialog.cancel()

            }
            builder.setNegativeButton(
                "No"
            ) { dialog, which -> dialog.cancel() }

            val alert = builder.create()
            alert.show()



        }


        fragmentTruckdetailsBinding.btnEdit.setOnClickListener {


            val bundle = Bundle()
            bundle.putString("truck_id",truckDetails.truckId)
            bundle.putString("truck_type_id",truckDetails.truckType)
            bundle.putString("truck_type_name",truckDetails.truckTypeName)
            bundle.putString("user_master_id",truckDetails.userId)
            bundle.putString("truck_vin_number",truckDetails.truckVinNumber)
            bundle.putString("truck_reg_number",truckDetails.truckRegNumber)
            bundle.putString("truck_make",truckDetails.truckMake)
            bundle.putString("truck_body_style",truckDetails.truckBodyStyle)
            bundle.putString("truck_model",truckDetails.truckModel)
            bundle.putString("truck_color",truckDetails.truckColor)
            bundle.putString("truck_cabin_type",truckDetails.truckCabinType)
            bundle.putString("truck_empty_weight",truckDetails.truckEmptyWeight)
            bundle.putString("truck_carring_capacity",truckDetails.truckCarringCapacity)
            bundle.putString("truck_weight_metric",truckDetails.truckWeightMetric)
            bundle.putString("company_trucknumber",truckDetails.companyTrucknumber)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_truckeditdetail,bundle)

        }
        getTruckDetails(truck_id)
    }

    private fun getTruckDetails(truckId: String) {


        if (CheckConnectivity.getInstance(mainActivity).isOnline) {

            viewModel.getTruckDetails(
                truckId = truckId
            ).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            mainActivity.hideProgressDialog()
                            if (resource.data?.status?.errorCode == 0) {

                                resource.data.result.truckDetails.let {itDetails->

                                    truckDetails=itDetails
                                    with(fragmentTruckdetailsBinding){
                                        Glide.with(mainActivity)
                                            .load(ApiClient.BASE_URL+itDetails.truckimage)
                                            .timeout(6000)
                                            .error(R.drawable.logo)
                                            .placeholder(R.drawable.logo)
                                            .into(ivTruckImg)

                                        tvTruckModel.text=itDetails.truckModel
                                        tvTruckNo.text=itDetails.truckRegNumber
                                        tvTruckType.text=itDetails.truckTypeName
                                        tvTruckEngine.text=itDetails.truckMake
                                        tvTruckColor.text=itDetails.truckColor
                                    }

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


    private fun deleteTruck(truck_id: String, view: View){

        if (CheckConnectivity.getInstance(mainActivity).isOnline) {

            viewModel.deleteTruck(truck_id = truck_id
            ).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            mainActivity.hideProgressDialog()
                            if (resource.data?.status?.errorCode?.equals(0)!!) {
                                Toast.makeText(mainActivity, it.data?.status?.message, Toast.LENGTH_SHORT).show()
                                val navController = Navigation.findNavController(view)
                                navController.navigate(R.id.nav_home)

                            } else {

                                val builder = AlertDialog.Builder(mainActivity)
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

            Toast.makeText(mainActivity, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT).show()

        }


    }
}