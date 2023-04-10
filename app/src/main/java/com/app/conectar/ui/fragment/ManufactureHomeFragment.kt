package com.app.conectar.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.app.conectar.R
import com.app.conectar.databinding.FragmentFleetHomeBinding
import com.app.conectar.databinding.FragmentManufactureHomeBinding
import com.app.conectar.session.SessionManager
import com.app.conectar.ui.activities.LoginphoneActivity
import com.app.conectar.ui.activities.MainActivity
import com.app.conectar.ui.activities.RegistrationDetails
import com.app.conectar.ui.activities.RegistrationHome

class ManufactureHomeFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentManufactureHomeBinding: FragmentManufactureHomeBinding
    var sessionManager: SessionManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentManufactureHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_manufacture_home,container,false)
        val root = fragmentManufactureHomeBinding.root
        mainActivity = activity as MainActivity
        sessionManager = SessionManager(mainActivity)

        fragmentManufactureHomeBinding.topnav.tvNavtitle.text = "Manufacture"

        fragmentManufactureHomeBinding.btnJoblist.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_joblist)

        }

        fragmentManufactureHomeBinding.btnCompletedjobs.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_completejoblist)
        }



        fragmentManufactureHomeBinding.btnAlerts.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_alert)
        }


        fragmentManufactureHomeBinding.btnContractor.setOnClickListener {

            if (sessionManager?.getUser().equals("ContractorRegistration")){

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_contractorhome)

            }else{

                val intent = Intent(mainActivity, RegistrationHome::class.java)
                startActivity(intent)

            }



        }


        fragmentManufactureHomeBinding.btnManufacturer.setOnClickListener {
            if (sessionManager?.getUser().equals("MaterialRegistration")){

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_manufacturehome)

            }else{

                val intent = Intent(mainActivity, RegistrationHome::class.java)
                startActivity(intent)

            }

        }


        fragmentManufactureHomeBinding.btnFleet.setOnClickListener {

            if (sessionManager?.getUser().equals("IndependntTruckRegistration")){

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_fleethome)

            }else{

                val intent = Intent(mainActivity, RegistrationHome::class.java)
                startActivity(intent)

            }


        }



        fragmentManufactureHomeBinding.topnav.btnLogout.setOnClickListener {

            sessionManager?.logoutUser()
            val intent = Intent(mainActivity, LoginphoneActivity::class.java)
            startActivity(intent)

        }



        return root
    }

}