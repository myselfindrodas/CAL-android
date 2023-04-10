package com.app.conectar.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.conectar.R
import com.app.conectar.databinding.FragmentDocuploadBinding
import com.app.conectar.ui.activities.MainActivity

class DocuploadFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentDocuploadBinding: FragmentDocuploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentDocuploadBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_docupload,container,false)
        val root = fragmentDocuploadBinding.root
        mainActivity = activity as MainActivity


        fragmentDocuploadBinding.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }

        return root
    }

}