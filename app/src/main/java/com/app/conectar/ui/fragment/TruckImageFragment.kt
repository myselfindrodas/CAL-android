package com.app.conectar.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.conectar.R
import com.app.conectar.databinding.FragmentTruckImageBinding
import com.app.conectar.ui.activities.MainActivity

class TruckImageFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentTruckImageBinding: FragmentTruckImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentTruckImageBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_truck_image, container, false)
        val root = fragmentTruckImageBinding.root

        mainActivity = activity as MainActivity


        fragmentTruckImageBinding.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }

        return root
    }

}