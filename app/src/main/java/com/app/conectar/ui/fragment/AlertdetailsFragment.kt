package com.app.conectar.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.conectar.R
import com.app.conectar.databinding.FragmentAlertdetailsBinding
import com.app.conectar.ui.activities.MainActivity

class AlertdetailsFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentAlertdetailsBinding: FragmentAlertdetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentAlertdetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_alertdetails, container, false)

        val root = fragmentAlertdetailsBinding.root
        mainActivity = activity as MainActivity


        fragmentAlertdetailsBinding.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }

        return root
    }

}