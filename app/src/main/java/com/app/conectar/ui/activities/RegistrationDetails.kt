package com.app.conectar.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.ActivityRegistrationDetailsBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.model.citylistresponse.CityModel
import com.app.conectar.model.statelistresponse.StateModel
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.utils.Constant
import com.app.conectar.utils.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegistrationDetails : AppCompatActivity() {

    lateinit var activityRegistrationDetailsBinding: ActivityRegistrationDetailsBinding
    var sessionManager: SessionManager? = null
    var stateid: String? = ""
    var cityid: String? = ""
    var selectedstatename: String = ""
    var selectedcityname: String = ""
    val statename = ArrayList<String>()
    val cityname = ArrayList<String>()
    private lateinit var viewModel: CommonViewModel
    var statelistModelArrayList: ArrayList<StateModel> = ArrayList<StateModel>()
    var citylistModelArrayList: ArrayList<CityModel> = ArrayList<CityModel>()
    var user_role:String? = ""
    var REQUEST_CODE = 101
    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    var locationManager: LocationManager? = null
    var latitude: String? = ""
    var longitude: String? = ""
    private val AUTOCOMPLETE_REQUEST_CODE = 1
    var placesClient: PlacesClient? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRegistrationDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_registration_details)
        sessionManager = SessionManager(this)
        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        if (!Places.isInitialized()) {
            Places.initialize(this, getString(R.string.api_key))
        }
        placesClient = Places.createClient(this)


        locationManager = getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (!locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            || !locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
            || !locationManager!!.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)
        ) {

            OnGPS()

        } else {
            getLocation()
        }


        activityRegistrationDetailsBinding.etLocation.setOnClickListener {
            openSearchBar()
        }

        if (sessionManager?.getUser().equals("IndependntTruckRegistration")) {

            activityRegistrationDetailsBinding.llcompanyName.visibility = View.GONE
            activityRegistrationDetailsBinding.llcompanyTaxid.visibility = View.GONE
            activityRegistrationDetailsBinding.llusertype.visibility = View.VISIBLE
            activityRegistrationDetailsBinding.llLocation.visibility = View.VISIBLE


        } else if (sessionManager?.getUser().equals("MaterialRegistration")) {

            activityRegistrationDetailsBinding.llcompanyName.visibility = View.GONE
            activityRegistrationDetailsBinding.llcompanyTaxid.visibility = View.GONE
            activityRegistrationDetailsBinding.llusertype.visibility = View.GONE
            activityRegistrationDetailsBinding.llLocation.visibility = View.GONE


        } else if (sessionManager?.getUser().equals("ContractorRegistration")) {

            activityRegistrationDetailsBinding.llcompanyName.visibility = View.VISIBLE
            activityRegistrationDetailsBinding.llcompanyTaxid.visibility = View.VISIBLE
            activityRegistrationDetailsBinding.llusertype.visibility = View.GONE
            activityRegistrationDetailsBinding.llLocation.visibility = View.GONE
        }




        activityRegistrationDetailsBinding.etFirstname.setText(sessionManager?.getFirstname() ?: "")
        activityRegistrationDetailsBinding.etLastname.setText(sessionManager?.getLastname() ?: "")
        activityRegistrationDetailsBinding.etMobilenumber.setText(sessionManager?.getMobile() ?: "")
        activityRegistrationDetailsBinding.etEmail.setText(sessionManager?.getUsername() ?: "")

        if (activityRegistrationDetailsBinding.etFirstname.text.toString().length>0){
            activityRegistrationDetailsBinding.etFirstname.isEnabled = false
        }else{
            activityRegistrationDetailsBinding.etFirstname.isEnabled = true

        }


        if (activityRegistrationDetailsBinding.etLastname.text.toString().length>0){
            activityRegistrationDetailsBinding.etLastname.isEnabled = false
        }else{
            activityRegistrationDetailsBinding.etLastname.isEnabled = true

        }


        if (activityRegistrationDetailsBinding.etMobilenumber.text.toString().length>0){
            activityRegistrationDetailsBinding.etMobilenumber.isEnabled = false
        }else{
            activityRegistrationDetailsBinding.etMobilenumber.isEnabled = true

        }


        if (activityRegistrationDetailsBinding.etEmail.text.toString().length>0){
            activityRegistrationDetailsBinding.etEmail.isEnabled = false
        }else{
            activityRegistrationDetailsBinding.etEmail.isEnabled = true
        }



        activityRegistrationDetailsBinding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginphoneActivity::class.java))

        }
        activityRegistrationDetailsBinding.llSignup.setOnClickListener {

            if (sessionManager?.getUser().equals("IndependntTruckRegistration") && validateTruckRegistration()){

                when {
                    activityRegistrationDetailsBinding.radioIndependent.isChecked -> {
                        user_role = "4"
                    }
                    activityRegistrationDetailsBinding.radioFleet.isChecked -> {
                        user_role = "5"
                    }
                }

                registration(activityRegistrationDetailsBinding.etMobilenumber.text.toString(), "MOB", "",
                activityRegistrationDetailsBinding.etFirstname.text.toString(), stateid!!,
                activityRegistrationDetailsBinding.etEmail.text.toString(),
                activityRegistrationDetailsBinding.etLastname.text.toString(),
                activityRegistrationDetailsBinding.etconfirmPassword.text.toString(), user_role!!,
                    activityRegistrationDetailsBinding.etCompanyname.text.toString(),cityid!!, latitude.toString(), longitude.toString())


            } else if (sessionManager?.getUser().equals("MaterialRegistration") && validateMaterialRegistration()){

                registration(activityRegistrationDetailsBinding.etMobilenumber.text.toString(), "MOB", "",
                    activityRegistrationDetailsBinding.etFirstname.text.toString(), stateid!!,
                    activityRegistrationDetailsBinding.etEmail.text.toString(),
                    activityRegistrationDetailsBinding.etLastname.text.toString(),
                    activityRegistrationDetailsBinding.etconfirmPassword.text.toString(), "3",
                    activityRegistrationDetailsBinding.etCompanyname.text.toString(),cityid!!,  latitude.toString(), longitude.toString())

            }else if (sessionManager?.getUser().equals("ContractorRegistration") && validateContractorRegistration()){

                registration(activityRegistrationDetailsBinding.etMobilenumber.text.toString(), "MOB",
                    activityRegistrationDetailsBinding.etCompanytaxid.text.toString(),
                    activityRegistrationDetailsBinding.etFirstname.text.toString(), stateid!!,
                    activityRegistrationDetailsBinding.etEmail.text.toString(),
                    activityRegistrationDetailsBinding.etLastname.text.toString(),
                    activityRegistrationDetailsBinding.etconfirmPassword.text.toString(), "2",
                    activityRegistrationDetailsBinding.etCompanyname.text.toString(),cityid!!,  latitude.toString(), longitude.toString())

            }

        }


        activityRegistrationDetailsBinding.topnav.btnBack.setOnClickListener {

            onBackPressedDispatcher.onBackPressed()
        }



        spstate()


        activityRegistrationDetailsBinding.spState.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long,
            ) {
                if (position > 0) {
                    stateid = statelistModelArrayList.get(position - 1).stateId
                    selectedstatename = statelistModelArrayList.get(position - 1).stateName
                    Log.d(ContentValues.TAG, "stateid --->" + stateid)
                    Log.d(ContentValues.TAG, "statename --->" + selectedstatename)
                    spcity(stateid!!)
                    cityid = ""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })




        activityRegistrationDetailsBinding.spCity.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long,
            ) {
                if (position > 0) {
                    cityid = citylistModelArrayList.get(position - 1).cityid
                    selectedcityname = citylistModelArrayList.get(position - 1).cityName
                    Log.d(ContentValues.TAG, "cityid --->" + cityid)
                    Log.d(ContentValues.TAG, "cityname --->" + selectedcityname)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })


    }

    private fun openSearchBar() {

        val fields = Arrays.asList(Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG)
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .build(this)
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)
                val address = place.address

                val lati = place.latLng!!.latitude.toString() + ""
                val longi = place.latLng!!.longitude.toString() + ""
                latitude = lati
                longitude = longi
                activityRegistrationDetailsBinding.etLocation.setText(address)

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                val status = Autocomplete.getStatusFromIntent(data!!)
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }




    private fun OnGPS() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton(
            "Yes"
        ) { dialog, which -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
            .setNegativeButton(
                "No"
            ) { dialog, which -> dialog.cancel() }
        val alertDialog = builder.create()
        alertDialog.show()
    }


    private fun getLocation() {

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE
            )
        } else {
            val locationGPS =
                locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (locationGPS != null) {
                val lat = locationGPS.latitude
                val longi = locationGPS.longitude
                latitude = lat.toString()
                longitude = longi.toString()

                Log.d(TAG, "latlon-->"+ latitude+" , "+longitude)

            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show()
            }
        }
    }



    @SuppressLint("SuspiciousIndentation")
    private fun spstate() {


        if (CheckConnectivity.getInstance(this).isOnline) {

            viewModel.getStateList().observe(this) {

                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            if (resource.data?.statestatus?.errorCode!!.equals(0)) {
                                statename.clear()
                                statelistModelArrayList.clear()
                                statename.add("Select State")
                                spcity2()
                                statelistModelArrayList = ArrayList<StateModel>()
                                for (i in it.data?.stateresult?.datalist!!) {
                                    statename.add("${i.state_name}")
                                    val StateModel = StateModel(i.state_id, i.state_name)
                                    statelistModelArrayList.add(StateModel)
                                }


                                val spinnerArrayAdapter:ArrayAdapter<String> =
                                    ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, statename)
                                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                    activityRegistrationDetailsBinding.spState.setAdapter(spinnerArrayAdapter)

                            } else {
                                val builder = AlertDialog.Builder(this)
                                builder.setMessage(resource.data.statestatus.message)
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
                            val builder = AlertDialog.Builder(this)
                            builder.setMessage(it.message)
                            builder.setPositiveButton(
                                "Ok"
                            ) { dialog, which ->

                                dialog.cancel()

                            }
                            val alert = builder.create()
                            alert.show()
//                        Toast.makeText(mainActivity, it.message, Toast.LENGTH_SHORT).show()

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


    private fun spcity(state_id:String){


        if (CheckConnectivity.getInstance(this).isOnline) {

            viewModel.getCityList(state_id = state_id).observe(this) {

                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            if (resource.data?.citystatus?.errorCode!!.equals(0)) {
                                cityname.clear()
                                citylistModelArrayList.clear()
                                cityname.add("Select City")
                                citylistModelArrayList = ArrayList<CityModel>()
                                for (i in it.data?.cityresult?.citydatalist!!) {
                                    cityname.add("${i.city_name}")
                                    val CityModel = CityModel(i.id, i.city_name)
                                    citylistModelArrayList.add(CityModel)
                                }


                                val spinnerArrayAdapter:ArrayAdapter<String> =
                                    ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cityname)
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                activityRegistrationDetailsBinding.spCity.setAdapter(spinnerArrayAdapter)

                            } else {
                                val builder = AlertDialog.Builder(this)
                                builder.setMessage(resource.data.citystatus.message)
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
                            val builder = AlertDialog.Builder(this)
                            builder.setMessage(it.message)
                            builder.setPositiveButton(
                                "Ok"
                            ) { dialog, which ->

                                dialog.cancel()

                            }
                            val alert = builder.create()
                            alert.show()
//                        Toast.makeText(mainActivity, it.message, Toast.LENGTH_SHORT).show()

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


    private fun spcity2() {

        cityname.clear()
        citylistModelArrayList.clear()
        cityname.add("Select City")
            val spinnerArrayAdapter: ArrayAdapter<String> =
                ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    cityname
                )
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            activityRegistrationDetailsBinding.spCity.setAdapter(spinnerArrayAdapter)


    }


    private fun validateTruckRegistration(): Boolean {
        if (activityRegistrationDetailsBinding.radiobutton.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Select user type", Toast.LENGTH_SHORT).show()
            return false
        } else if (activityRegistrationDetailsBinding.etFirstname.text.isEmpty()) {
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etFirstname.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etLastname.text.isEmpty()) {
            Toast.makeText(this, "Enter Last Name", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etLastname.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etMobilenumber.text.isEmpty()) {
            Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etMobilenumber.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etEmail.text.isEmpty()) {
            Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etEmail.requestFocus()
            return false
        } else if (!Constant.isValidEmail(activityRegistrationDetailsBinding.etEmail.text.toString())) {
            Toast.makeText(this, "Enter valid Email Id", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etEmail.requestFocus()
            return false
        } else if (!isValidPassword(activityRegistrationDetailsBinding.etPassword.text.trim().toString())) {
            Toast.makeText(this, "Enter Valid Password", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etPassword.requestFocus()
            return false
        } else if (!isValidPassword(activityRegistrationDetailsBinding.etconfirmPassword.text.trim().toString())) {
            Toast.makeText(this, "Enter Confirm Password", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etconfirmPassword.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etconfirmPassword.text.toString() != activityRegistrationDetailsBinding.etPassword.text.toString()) {
            Toast.makeText(this, "Password and Confirm Password doesn't match", Toast.LENGTH_SHORT)
                .show()
            activityRegistrationDetailsBinding.etconfirmPassword.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etLocation.text.isEmpty()) {
            Toast.makeText(this, "Enter Location", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etLocation.requestFocus()
            return false
        } else if (!activityRegistrationDetailsBinding.cbAgreement.isChecked) {
            Toast.makeText(this, "Check the terms and condition", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etLocation.requestFocus()
            return false
        } else
            return true

    }

    private fun validateMaterialRegistration(): Boolean {

        if (activityRegistrationDetailsBinding.etFirstname.text.isEmpty()) {
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etFirstname.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etLastname.text.isEmpty()) {
            Toast.makeText(this, "Enter Last Name", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etLastname.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etMobilenumber.text.isEmpty()) {
            Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etMobilenumber.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etEmail.text.isEmpty()) {
            Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etEmail.requestFocus()
            return false
        } else if (!Constant.isValidEmail(activityRegistrationDetailsBinding.etEmail.text.toString())) {
            Toast.makeText(this, "Enter valid Email Id", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etEmail.requestFocus()
            return false
        }else if (!isValidPassword(activityRegistrationDetailsBinding.etPassword.text.trim().toString())) {
            Toast.makeText(this, "Enter Valid Password", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etPassword.requestFocus()
            return false
        } else if (!isValidPassword(activityRegistrationDetailsBinding.etconfirmPassword.text.trim().toString())) {
            Toast.makeText(this, "Enter Confirm Password", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etconfirmPassword.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etconfirmPassword.text.toString() != activityRegistrationDetailsBinding.etPassword.text.toString()) {
            Toast.makeText(this, "Password and Confirm Password doesn't match", Toast.LENGTH_SHORT)
                .show()
            activityRegistrationDetailsBinding.etconfirmPassword.requestFocus()
            return false
        } else if (!activityRegistrationDetailsBinding.cbAgreement.isChecked) {
            Toast.makeText(this, "Check the terms and condition", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etLocation.requestFocus()
            return false
        } else
            return true
    }

    private fun validateContractorRegistration(): Boolean {

        if (activityRegistrationDetailsBinding.etFirstname.text.isEmpty()) {
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etFirstname.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etLastname.text.isEmpty()) {
            Toast.makeText(this, "Enter Last Name", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etLastname.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etCompanyname.text.isEmpty()) {
            Toast.makeText(this, "Enter Company Name", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etCompanyname.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etCompanytaxid.text.isEmpty()) {
            Toast.makeText(this, "Enter Company Tax Id", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etCompanytaxid.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etMobilenumber.text.isEmpty() || activityRegistrationDetailsBinding.etMobilenumber.text.toString().length < 7) {
            Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etMobilenumber.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etEmail.text.isEmpty()) {
            Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etEmail.requestFocus()
            return false
        } else if (!Constant.isValidEmail(activityRegistrationDetailsBinding.etEmail.text.toString())) {
            Toast.makeText(this, "Enter valid Email Id", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etEmail.requestFocus()
            return false
        }else if (!isValidPassword(activityRegistrationDetailsBinding.etPassword.text.trim().toString())) {
            Toast.makeText(this, "Enter Valid Password", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etPassword.requestFocus()
            return false
        } else if (!isValidPassword(activityRegistrationDetailsBinding.etconfirmPassword.text.trim().toString())) {
            Toast.makeText(this, "Enter Confirm Password", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etconfirmPassword.requestFocus()
            return false
        } else if (activityRegistrationDetailsBinding.etconfirmPassword.text.toString() != activityRegistrationDetailsBinding.etPassword.text.toString()) {
            Toast.makeText(this, "Password and Confirm Password doesn't match", Toast.LENGTH_SHORT)
                .show()
            activityRegistrationDetailsBinding.etconfirmPassword.requestFocus()
            return false
        } else if (!activityRegistrationDetailsBinding.cbAgreement.isChecked) {
            Toast.makeText(this, "Check the terms and condition", Toast.LENGTH_SHORT).show()
            activityRegistrationDetailsBinding.etLocation.requestFocus()
            return false
        } else
            return true
    }


    fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }


    private fun registration(mobile:String, registration_source:String, company_registration:String,
                             fname:String, state:String, email: String, lanme: String, password: String,
                             user_role: String, company_name: String, city: String, latitude:String, longitude:String){

        if (CheckConnectivity.getInstance(this).isOnline) {

            viewModel.registration(mobile = mobile, registration_source = registration_source,
                company_registration = company_registration, fname = fname, state = state, email = email,
                lanme = lanme, password = password, user_role = user_role, company_name = company_name,
                city = city, latitude = latitude, longitude = longitude
            ).observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            Log.d(ContentValues.TAG, "errorcode-->" + resource.data?.status?.errorCode)
                            if (resource.data?.status?.errorCode==0) {

                                val builder = AlertDialog.Builder(this@RegistrationDetails)
                                builder.setMessage(resource.data.status.message)
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    val intent = Intent(this, LoginphoneActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.show()
                            } else {

                                val builder = AlertDialog.Builder(this@RegistrationDetails)
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
                            Log.d(ContentValues.TAG, "print-->" + resource.data?.status?.errorCode)

                            val intent = Intent(this, LoginphoneActivity::class.java)
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