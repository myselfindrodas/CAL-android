package com.app.conectar.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.app.conectar.R
import com.app.conectar.databinding.FragmentContractorHomeBinding
import com.app.conectar.session.SessionManager
import com.app.conectar.ui.activities.LoginphoneActivity
import com.app.conectar.ui.activities.MainActivity
import com.app.conectar.ui.activities.RegistrationDetails
import com.app.conectar.ui.activities.RegistrationHome

class ContractorHomeFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentContractorHomeBinding: FragmentContractorHomeBinding
    var sessionManager: SessionManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentContractorHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_contractor_home,container,false)
        val root = fragmentContractorHomeBinding.root
        mainActivity = activity as MainActivity
        sessionManager = SessionManager(mainActivity)

        fragmentContractorHomeBinding.topnav.tvNavtitle.text = "Contractor"



//        fragmentContractorHomeBinding.btnJoblist.setOnClickListener {
//
//            val navController = Navigation.findNavController(it)
//            navController.navigate(R.id.nav_joblist)
//
//        }

//        fragmentContractorHomeBinding.btnCompletedjobs.setOnClickListener {
//
//            val navController = Navigation.findNavController(it)
//            navController.navigate(R.id.nav_completejoblist)
//        }


//        fragmentContractorHomeBinding.btnManagetruck.setOnClickListener {
//
//            val navController = Navigation.findNavController(it)
//            navController.navigate(R.id.nav_trucklist)
//        }


        fragmentContractorHomeBinding.btnAlerts.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_alert)
        }


//        fragmentContractorHomeBinding.btnCertifiedoc.setOnClickListener {
//
//            val navController = Navigation.findNavController(it)
//            navController.navigate(R.id.nav_doccertified)
//        }


        fragmentContractorHomeBinding.topnav.btnLogout.setOnClickListener {

            sessionManager?.logoutUser()
            val intent = Intent(mainActivity, LoginphoneActivity::class.java)
            startActivity(intent)

        }




        fragmentContractorHomeBinding.btnContractor.setOnClickListener {

            if (sessionManager?.getUser().equals("ContractorRegistration")){

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_contractorhome)

            }else{

                val intent = Intent(mainActivity, RegistrationHome::class.java)
                startActivity(intent)

            }



        }


        fragmentContractorHomeBinding.btnManufacturer.setOnClickListener {
            if (sessionManager?.getUser().equals("MaterialRegistration")){

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_manufacturehome)

            }else{

                val intent = Intent(mainActivity, RegistrationHome::class.java)
                startActivity(intent)

            }

        }


        fragmentContractorHomeBinding.btnFleet.setOnClickListener {

            if (sessionManager?.getUser().equals("IndependntTruckRegistration")){

                val navController = Navigation.findNavController(it)
                navController.navigate(R.id.nav_fleethome)

            }else{

                val intent = Intent(mainActivity, RegistrationHome::class.java)
                startActivity(intent)

            }


        }


        return root
    }

}