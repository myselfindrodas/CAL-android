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
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.FragmentEditTruckDetailsBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.model.edittruck_details.EditTruckDetailsRequestModel
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.ui.activities.MainActivity
import com.app.conectar.utils.Status
import kotlinx.android.synthetic.main.fragment_edit_truck_details.*


class EditTruckDetailsFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentEditTruckDetailsBinding: FragmentEditTruckDetailsBinding
    private lateinit var viewModel: CommonViewModel
    var sessionManager: SessionManager? = null
    var user_master_id = ""
    var truck_id = ""
    var truck_vin_number = ""
    var truck_reg_number = ""
    var truck_make = ""
    var truck_body_style = ""
    var truck_model = ""
    var truck_color = ""
    var truck_cabin_type = ""
    var truck_empty_weight = ""
    var truck_carring_capacity = ""
    var truck_weight_metric = ""
    var truck_type_id = ""
    var truck_type_name = ""
    var company_trucknumber = ""

    lateinit var editTruckDetailsRequestModel: EditTruckDetailsRequestModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentEditTruckDetailsBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_edit_truck_details,
                container,
                false
            )
        val root = fragmentEditTruckDetailsBinding.root

        mainActivity = activity as MainActivity
        sessionManager = SessionManager(mainActivity)

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        fragmentEditTruckDetailsBinding.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }

        val intent = arguments

        if (intent != null) {
            truck_id = intent.getString("truck_id")!!
            truck_type_id = intent.getString("truck_type_id")!!
            truck_type_name = intent.getString("truck_type_name")!!
            user_master_id = intent.getString("user_master_id")!!
            truck_vin_number = intent.getString("truck_vin_number")!!
            truck_reg_number = intent.getString("truck_reg_number")!!
            truck_make = intent.getString("truck_make")!!
            truck_body_style = intent.getString("truck_body_style")!!
            truck_model = intent.getString("truck_model")!!
            truck_color = intent.getString("truck_color")!!
            truck_cabin_type = intent.getString("truck_cabin_type")!!
            truck_empty_weight = intent.getString("truck_empty_weight")!!
            truck_carring_capacity = intent.getString("truck_carring_capacity")!!
            truck_weight_metric = intent.getString("truck_weight_metric")!!
            company_trucknumber = intent.getString("company_trucknumber")!!
            editTruckDetailsRequestModel = EditTruckDetailsRequestModel(
                truck_id,
                user_master_id,
                truck_vin_number,
                truck_reg_number,
                truck_make,
                truck_body_style,
                truck_model,
                truck_color,
                truck_cabin_type,
                truck_empty_weight,
                truck_carring_capacity,
                truck_weight_metric,
                truck_type_id,
                company_trucknumber
            )
        }

        fragmentEditTruckDetailsBinding.btnNext.setOnClickListener {

            if (etVinNo.text.toString().isNotEmpty()) {
                editTruckDetailsRequestModel.truck_vin_number = etVinNo.text.toString()
            }
            if (etVeicleRegNo.text.toString().isNotEmpty()) {
                editTruckDetailsRequestModel.truck_reg_number = etVeicleRegNo.text.toString()
            }
            if (etMake.text.toString().isNotEmpty()) {
                editTruckDetailsRequestModel.truck_make = etMake.text.toString()
            }
            if (etBodyStyle.text.toString().isNotEmpty()) {
                editTruckDetailsRequestModel.truck_body_style = etBodyStyle.text.toString()
            }
            if (etModel.text.toString().isNotEmpty()) {
                editTruckDetailsRequestModel.truck_model = etModel.text.toString()
            }
            if (etColor.text.toString().isNotEmpty()) {
                editTruckDetailsRequestModel.truck_color = etColor.text.toString()
            }

            updateTruckDetails(editTruckDetailsRequestModel)
            /*if (etTruckType.text.toString().isNotEmpty()){
                editTruckDetailsRequestModel.truck_type=etTruckType.text.toString()
            }*/
        }

        with(fragmentEditTruckDetailsBinding) {
            etVinNo.setText(truck_vin_number)
            etVeicleRegNo.setText(truck_reg_number)
            etMake.setText(truck_make)
            etBodyStyle.setText(truck_body_style)
            etModel.setText(truck_model)
            etColor.setText(truck_color)
            etTruckType.setText(truck_type_name)
        }


        return root
    }

    private fun updateTruckDetails(editTruckDetailsRequestModel: EditTruckDetailsRequestModel) {


        if (CheckConnectivity.getInstance(mainActivity).isOnline) {

            viewModel.updateTruckDetails(
                editTruckDetailsRequestModel = editTruckDetailsRequestModel
            ).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            mainActivity.hideProgressDialog()
                            if (resource.data?.status?.errorCode == 0) {
                                resource.data.result.userdata.let { itDetails->

                                    Toast.makeText(mainActivity, resource.data.status.message, Toast.LENGTH_SHORT).show()
                                    /*val navController = Navigation.findNavController(it)
                                    navController.navigate(R.id.nav_truckimageupload)*/
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
}