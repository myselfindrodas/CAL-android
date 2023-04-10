package com.app.conectar.ui.fragment

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.FragmentHomeBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.ui.activities.LoginphoneActivity
import com.app.conectar.ui.activities.MainActivity
import com.app.conectar.ui.activities.RegistrationDetails
import com.app.conectar.ui.activities.RegistrationHome
import com.app.conectar.utils.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.Task

class HomeFragment : Fragment(), OnMapReadyCallback {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentHomeBinding: FragmentHomeBinding
    var sessionManager: SessionManager? = null
    var REQUEST_CODE = 101
    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    var locationManager: LocationManager? = null
    var latitude: String? = ""
    var longitude: String? = ""
    var currentLocation: Location? = null
    private var mMap: GoogleMap? = null
    private var locationMarker: Marker? = null
    private lateinit var viewModel: CommonViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val root = fragmentHomeBinding.root
        mainActivity = activity as MainActivity
        sessionManager = SessionManager(mainActivity)
        val mapFragment = childFragmentManager.findFragmentById(R.id.current_location) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        locationManager = mainActivity.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mainActivity)


        locationManager = mainActivity.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mainActivity)

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            || !locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
            || !locationManager!!.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)
        ) {

            OnGPS()

        } else {
            getLocation()
        }


        fragmentHomeBinding.topnav.tvNavtitle.text = "Home"




        if (sessionManager?.getUser().equals("IndependntTruckRegistration")) {


            fragmentHomeBinding.rlfleet.rlfleetmenu.visibility = View.GONE
            fragmentHomeBinding.btnManufacturerSupplier.visibility = View.GONE
            fragmentHomeBinding.llSignupfleet.visibility = View.VISIBLE
            fragmentHomeBinding.llSignupcontractor.visibility = View.VISIBLE
            fragmentHomeBinding.llSignupindependent.visibility = View.GONE


            fragmentHomeBinding.btnContractor.setOnClickListener {


//                fragmentHomeBinding.rlindependent.rlindependentmenu.visibility = View.GONE
//                fragmentHomeBinding.rlcontructor.rlcontructormenu.visibility = View.VISIBLE
//                fragmentHomeBinding.llsecondrow.visibility = View.GONE
//                fragmentHomeBinding.hsadview.visibility = View.GONE
//                fragmentHomeBinding.llgap.visibility = View.VISIBLE
//                fragmentHomeBinding.topnav.tvNavtitle.text = "Contractor / Builder"
                sessionManager?.setUser("ContractorRegistration")
                val intent = Intent(mainActivity, RegistrationDetails::class.java)
                startActivity(intent)

            }


            fragmentHomeBinding.btnIndependent.setOnClickListener {


                fragmentHomeBinding.rlindependent.rlindependentmenu.visibility = View.VISIBLE
                fragmentHomeBinding.rlcontructor.rlcontructormenu.visibility = View.GONE
                fragmentHomeBinding.llsecondrow.visibility = View.GONE
                fragmentHomeBinding.hsadview.visibility = View.GONE
                fragmentHomeBinding.llgap.visibility = View.VISIBLE
                fragmentHomeBinding.topnav.tvNavtitle.text = "Independent"


            }


            fragmentHomeBinding.btnFleet.setOnClickListener {

                sessionManager?.setUser("IndependntTruckRegistration")
                val intent = Intent(mainActivity, RegistrationDetails::class.java)
                startActivity(intent)
            }


        } else if (sessionManager?.getUser().equals("ContractorRegistration")) {


            fragmentHomeBinding.rlfleet.rlfleetmenu.visibility = View.GONE
            fragmentHomeBinding.btnManufacturerSupplier.visibility = View.GONE
            fragmentHomeBinding.llSignupfleet.visibility = View.VISIBLE
            fragmentHomeBinding.llSignupcontractor.visibility = View.GONE
            fragmentHomeBinding.llSignupindependent.visibility = View.VISIBLE

            fragmentHomeBinding.btnContractor.setOnClickListener {


                fragmentHomeBinding.topnav.tvNavtitle.text = "Contractor / Builder"
                fragmentHomeBinding.rlindependent.rlindependentmenu.visibility = View.GONE
                fragmentHomeBinding.rlcontructor.rlcontructormenu.visibility = View.VISIBLE
                fragmentHomeBinding.llsecondrow.visibility = View.GONE
                fragmentHomeBinding.hsadview.visibility = View.GONE
                fragmentHomeBinding.llgap.visibility = View.VISIBLE



            }


            fragmentHomeBinding.btnIndependent.setOnClickListener {


//                fragmentHomeBinding.topnav.tvNavtitle.text = "Independent"
//                fragmentHomeBinding.rlindependent.rlindependentmenu.visibility = View.VISIBLE
//                fragmentHomeBinding.rlcontructor.rlcontructormenu.visibility = View.GONE
//                fragmentHomeBinding.llsecondrow.visibility = View.GONE
//                fragmentHomeBinding.hsadview.visibility = View.GONE
//                fragmentHomeBinding.llgap.visibility = View.VISIBLE

                sessionManager?.setUser("IndependntTruckRegistration")
                val intent = Intent(mainActivity, RegistrationDetails::class.java)
                startActivity(intent)

            }


            fragmentHomeBinding.btnFleet.setOnClickListener {

                sessionManager?.setUser("IndependntTruckRegistration")
                val intent = Intent(mainActivity, RegistrationDetails::class.java)
                startActivity(intent)
            }


        } else if (sessionManager?.getUser().equals("FleetRegistration")) {



            fragmentHomeBinding.btnManufacturerSupplier.visibility = View.GONE
            fragmentHomeBinding.llSignupfleet.visibility = View.GONE
            fragmentHomeBinding.llSignupcontractor.visibility = View.VISIBLE
            fragmentHomeBinding.llSignupindependent.visibility = View.VISIBLE


            fragmentHomeBinding.btnContractor.setOnClickListener {

                sessionManager?.setUser("ContractorRegistration")
                val intent = Intent(mainActivity, RegistrationDetails::class.java)
                startActivity(intent)

            }


            fragmentHomeBinding.btnIndependent.setOnClickListener {

                sessionManager?.setUser("IndependntTruckRegistration")
                val intent = Intent(mainActivity, RegistrationDetails::class.java)
                startActivity(intent)
            }


            fragmentHomeBinding.btnFleet.setOnClickListener {


                fragmentHomeBinding.topnav.tvNavtitle.text = "Fleet"
                fragmentHomeBinding.llsecondrow.visibility = View.GONE
                fragmentHomeBinding.rlfleet.rlfleetmenu.visibility = View.VISIBLE
                fragmentHomeBinding.hsadview.visibility = View.GONE
                fragmentHomeBinding.llgap.visibility = View.VISIBLE


            }


        } else if (sessionManager?.getUser().equals("MaterialRegistration")) {

            fragmentHomeBinding.btnManufacturerSupplier.visibility = View.VISIBLE
            fragmentHomeBinding.btnContractor.visibility = View.GONE
            fragmentHomeBinding.btnIndependent.visibility = View.INVISIBLE
            fragmentHomeBinding.btnFleet.visibility = View.INVISIBLE
            fragmentHomeBinding.rlindependent.rlindependentmenu.visibility = View.GONE
            fragmentHomeBinding.rlfleet.rlfleetmenu.visibility = View.GONE


            fragmentHomeBinding.btnManufacturerSupplier.setOnClickListener {

                fragmentHomeBinding.topnav.tvNavtitle.text = "Manufacturer /Supplier"
                fragmentHomeBinding.llsecondrow.visibility = View.GONE
                fragmentHomeBinding.rlcontructor.rlcontructormenu.visibility = View.VISIBLE
                fragmentHomeBinding.hsadview.visibility = View.GONE
                fragmentHomeBinding.llgap.visibility = View.VISIBLE

            }

        }


        onClick()
    }

    private fun OnGPS() {
        val builder = AlertDialog.Builder(mainActivity)
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
                mainActivity, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                mainActivity,
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
                fetchLocation()
            } else {
                Toast.makeText(mainActivity, "Unable to find location.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                mainActivity, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                mainActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE
            )
            return
        }
        val task: Task<Location> = fusedLocationProviderClient!!.getLastLocation()
        task.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location
//                Toast.makeText(mainActivity,
//                    currentLocation!!.getLatitude()
//                        .toString() + "" + currentLocation!!.getLongitude(),
//                    Toast.LENGTH_SHORT
//                ).show();


            }
        }
    }


    private fun onClick() {


        fragmentHomeBinding.topnav.btnLogout.setOnClickListener {


            val builder = AlertDialog.Builder(mainActivity)
            builder.setMessage("Do you want to logout?")
            builder.setPositiveButton(
                "Ok"
            ) { dialog, which ->
                logout(sessionManager?.getUserId()!!)
                dialog.cancel()
            }

            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
            val alert = builder.create()
            alert.show()


        }


        fragmentHomeBinding.rlindependent.btnCertifiedoc.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_doccertified)
        }


        fragmentHomeBinding.rlindependent.btnManagetruck.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_trucklist)
        }


        fragmentHomeBinding.rlindependent.btnCompletedjobs.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_completejoblist)
        }


        fragmentHomeBinding.rlindependent.btnJoblist.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_joblist)
        }


        fragmentHomeBinding.rlindependent.btnAlerts.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_alert)
        }


        fragmentHomeBinding.rlindependent.btnratings.setOnClickListener {


        }


        fragmentHomeBinding.rlindependent.btnFuelconnect.setOnClickListener {



        }


        fragmentHomeBinding.rlindependent.btnroadside.setOnClickListener {


        }


        fragmentHomeBinding.rlfleet.btnJoblist.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_joblist)
        }


        fragmentHomeBinding.rlfleet.btnCompletedjobs.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_completejoblist)
        }


        fragmentHomeBinding.rlfleet.btnCanceljobs.setOnClickListener {


        }


        fragmentHomeBinding.rlfleet.btnManagetruck.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_trucklist)
        }


        fragmentHomeBinding.rlfleet.btnManageDrivers.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_driverlist)
        }


        fragmentHomeBinding.rlfleet.btnAlerts.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_alert)
        }


        fragmentHomeBinding.rlfleet.btnCertifiedoc.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_doccertified)
        }


        fragmentHomeBinding.rlfleet.btnratings.setOnClickListener {


        }



        fragmentHomeBinding.rlfleet.btnFuelconnect.setOnClickListener {

        }



        fragmentHomeBinding.rlfleet.btnroadside.setOnClickListener {

        }


        fragmentHomeBinding.rlcontructor.btnPostjobs.setOnClickListener {


        }


        fragmentHomeBinding.rlcontructor.btnManagejobs.setOnClickListener {


        }


        fragmentHomeBinding.rlcontructor.btnTracktrucklive.setOnClickListener {


        }


        fragmentHomeBinding.rlcontructor.btnTruckinArea.setOnClickListener {


        }


        fragmentHomeBinding.rlcontructor.btnAlerts.setOnClickListener {


            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_alert)

        }


        fragmentHomeBinding.rlcontructor.btnratings.setOnClickListener {


        }


        fragmentHomeBinding.rlcontructor.btnAvailableMaterials.setOnClickListener {


        }


        fragmentHomeBinding.rlcontructor.btnFuelConnect.setOnClickListener {


        }



        fragmentHomeBinding.btnFuelconnect.setOnClickListener {

            alertdialouge()

        }


        fragmentHomeBinding.btnConnerctaload.setOnClickListener {

            alertdialouge()

        }


        fragmentHomeBinding.btnRoadserviceconnect.setOnClickListener {

            alertdialouge()

        }
    }


    private fun logout(userid:String){

        if (CheckConnectivity.getInstance(mainActivity).isOnline) {

            viewModel.logout(user_id = userid).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            mainActivity.hideProgressDialog()
                            if (resource.data?.status?.errorCode?.equals(0)!!){
                                sessionManager?.logoutUser()
                                val intent = Intent(mainActivity, LoginphoneActivity::class.java)
                                startActivity(intent)
                            }else{

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

            Toast.makeText(mainActivity, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT)
                .show()

        }


    }



    private fun alertdialouge(){

        val builder = AlertDialog.Builder(mainActivity)
        builder.setMessage("New features will come soon.")
        builder.setPositiveButton(
            "Ok"
        ) { dialog, which ->

            dialog.cancel()
        }

        val alert = builder.create()
        alert.show()

    }





    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    mainActivity.finishAffinity()
                    if (activity != null) {
                        activity?.finish()
                    }

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0
        val cameraPosition = CameraPosition.Builder().target(
            LatLng(latitude!!.toDouble(), longitude!!.toDouble())
        ).zoom(17f).build()
        mMap?.animateCamera(
            CameraUpdateFactory.newCameraPosition(
                cameraPosition
            )
        )

        val marker = MarkerOptions().position(
            LatLng(
                latitude!!.toDouble(), longitude!!.toDouble()
            )
        ).icon(bitmapDescriptorFromVector(mainActivity,R.drawable.ic_marker2))

        locationMarker = mMap?.addMarker(

            marker
        )

    }


    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.getIntrinsicWidth(),
            vectorDrawable.getIntrinsicHeight()
        );
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.getIntrinsicWidth(),
            vectorDrawable.getIntrinsicHeight(),
            Bitmap.Config.ARGB_8888
        );
        val canvas = Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


}