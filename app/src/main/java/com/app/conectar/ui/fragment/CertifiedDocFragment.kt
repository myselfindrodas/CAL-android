package com.app.conectar.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.app.conectar.R
import com.app.conectar.databinding.FragmentCertifiedDocBinding
import com.app.conectar.ui.activities.MainActivity

class CertifiedDocFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentCertifiedDocBinding: FragmentCertifiedDocBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentCertifiedDocBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_certified_doc, container, false)
        val root = fragmentCertifiedDocBinding.root

        mainActivity = activity as MainActivity

        fragmentCertifiedDocBinding.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }


        fragmentCertifiedDocBinding.layoutdoc.btnDocupload.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_docuploadimage)
        }


        return root
    }

}