package com.app.conectar.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.conectar.R
import com.app.conectar.databinding.FragmentCompletedjobBinding
import com.app.conectar.ui.activities.MainActivity


class CompletedjobFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentCompletedjobBinding: FragmentCompletedjobBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentCompletedjobBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_completedjob,container,false)

        val root = fragmentCompletedjobBinding.root
        mainActivity = activity as MainActivity


        fragmentCompletedjobBinding.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }

        return root
    }

}