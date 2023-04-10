package com.app.conectar.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.app.conectar.R
import com.app.conectar.api.modelfactory.CommonModelFactory
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.databinding.FragmentJoblistBinding
import com.app.conectar.internet.CheckConnectivity
import com.app.conectar.model.newjobresponse.NewJobModel
import com.app.conectar.model.newjobresponse.NewJoblist
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.retrofit.ApiHelper
import com.app.conectar.session.SessionManager
import com.app.conectar.ui.activities.MainActivity
import com.app.conectar.ui.adapter.AlertListAdapter
import com.app.conectar.ui.adapter.NewJobListAdapter
import com.app.conectar.utils.Status
import com.google.android.material.tabs.TabLayout

class JoblistFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentJoblistBinding: FragmentJoblistBinding
    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    private lateinit var layouts: IntArray
    lateinit var newJobListAdapter: NewJobListAdapter

    private lateinit var viewModel: CommonViewModel
    var sessionManager: SessionManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentJoblistBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_joblist, container, false)
        val root = fragmentJoblistBinding.root
        mainActivity = activity as MainActivity
        sessionManager = SessionManager(mainActivity)

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm

        myViewPagerAdapter = MyViewPagerAdapter()

        newJobListAdapter = NewJobListAdapter(requireContext())
        layouts = intArrayOf(
            R.layout.layout_new_job,
            R.layout.layout_new_job,
            R.layout.layout_new_job,
            R.layout.layout_new_job

        )


        fragmentJoblistBinding.tablayout.addTab(
            fragmentJoblistBinding.tablayout.newTab().setText("New")
        )
        fragmentJoblistBinding.tablayout.addTab(
            fragmentJoblistBinding.tablayout.newTab().setText("Hidden")
        )
        fragmentJoblistBinding.tablayout.addTab(
            fragmentJoblistBinding.tablayout.newTab().setText("Accepted")
        )
        fragmentJoblistBinding.tablayout.addTab(
            fragmentJoblistBinding.tablayout.newTab().setText("Rejected")
        )
        fragmentJoblistBinding.tablayout.setTabGravity(TabLayout.GRAVITY_FILL)

        val adapter = myViewPagerAdapter
        fragmentJoblistBinding.viewPager.setAdapter(adapter)
        fragmentJoblistBinding.viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                fragmentJoblistBinding.tablayout
            )
        )

        fragmentJoblistBinding.viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

//                when {
//                    position.equals(0) -> {
//                        fragmentJoblistBinding.rvNewjoblist.adapter = newJobListAdapter
//                    }
//                    position.equals(1) -> {
//                        fragmentJoblistBinding.rvNewjoblist.adapter = newJobListAdapter
//                    }
//                    position.equals(2) -> {
//                        fragmentJoblistBinding.rvNewjoblist.adapter = newJobListAdapter
//
//                    }
//                    position.equals(3) -> {
//                        fragmentJoblistBinding.rvNewjoblist.adapter = newJobListAdapter
//
//                    }
//                }


            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })


        fragmentJoblistBinding.tablayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                fragmentJoblistBinding.viewPager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


        fragmentJoblistBinding.btnBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }


        return root
    }


    private fun setupRecyclewrView(recyclerView: RecyclerView) {
        val mLayoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(mainActivity, 1)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = newJobListAdapter
    }


    private fun newJoblist() {


        if (CheckConnectivity.getInstance(mainActivity).isOnline) {

            viewModel.fleetnewjoblist(user_master_id = "1821")
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()
                                resource.data?.resultjoblistnew?.let { itProfileInfo ->


                                    newJobListAdapter.updateList(itProfileInfo.newJoblist)
                                    /* with(fragmentManageDriverBinding) {

                                         Glide.with(mainActivity)
                                             .load(itProfileInfo.profile.profileImage)
                                             .timeout(6000)
                                             .error(com.app.conectar.R.drawable.logo)
                                             .placeholder(com.app.conectar.R.drawable.logo)
                                             .into(PrfImg)


                                     }*/


                                }

                            }
                            Status.ERROR -> {
                                mainActivity.hideProgressDialog()
                                val builder = AlertDialog.Builder(mainActivity)
                                builder.setMessage(it.message)
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.show()
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


    inner class MyViewPagerAdapter : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater =
                mainActivity.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater!!.inflate(layouts[position], container, false)
            val newjoblistrecycler = view.findViewById<RecyclerView>(R.id.rvNewjoblist)
            setupRecyclewrView(newjoblistrecycler)
            newJoblist()
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return layouts.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }

}