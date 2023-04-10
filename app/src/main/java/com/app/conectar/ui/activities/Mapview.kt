package com.app.conectar.ui.activities

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.ActivityMapviewBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.model.myjobsresponse.Job
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.utils.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class Mapview : AppCompatActivity(), OnMapReadyCallback {

    lateinit var mapviewBinding: ActivityMapviewBinding
    var sessionManager: SessionManager? = null
    private lateinit var viewModel: CommonViewModel
    private var list: ArrayList<Job> = ArrayList()
    private var locationMarker: Marker? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_mapview)
        sessionManager = SessionManager(this)
        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm


        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)


        mapviewBinding.btnLoginemail.setOnClickListener {

            startActivity(Intent(this, LoginemailActivity::class.java))

        }


        mapviewBinding.btnRegstrationHome.setOnClickListener {

            startActivity(Intent(this, RegistrationHome::class.java))

        }


        mapviewBinding.btnDriverjob.setOnClickListener {

            startActivity(Intent(this, DriverjobAddress::class.java))

        }


        mapviewBinding.btnBack.setOnClickListener {

            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        if (CheckConnectivity.getInstance(this).isOnline) {

            viewModel.myjob(
                user_master_id = sessionManager?.getUserId().toString()
            ).observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            list = ArrayList<Job>()
                            for (i in it.data?.result?.jobs!!) {

                                val cameraPosition = CameraPosition.Builder().target(
                                    LatLng(i.sourceLat.toDouble(), i.sourceLong.toDouble())
                                ).zoom(9f).build()
                                googleMap.animateCamera(
                                    CameraUpdateFactory.newCameraPosition(
                                        cameraPosition
                                    )
                                )

                                val marker = MarkerOptions().position(
                                    LatLng(
                                        i.sourceLat.toDouble(),
                                        i.sourceLong.toDouble()
                                    )
                                ).icon(bitmapDescriptorFromVector(this,R.drawable.ic_marker))

                                locationMarker = googleMap.addMarker(

                                    marker
                                )


                            }


                        }
                        Status.ERROR -> {
                            hideProgressDialog()
                            Log.d(ContentValues.TAG, "print-->" + resource.data?.status)
                            val intent = Intent(this, Mapview::class.java)
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


//        val newyork = LatLng(40.81324544817638, -73.96253511015739)
//        googleMap.addMarker(
//            MarkerOptions()
//                .position(newyork)
//                .title("New York, NY")
//        )
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(newyork))
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newyork, 18f))

    }



    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
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