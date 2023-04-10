package com.app.conectar.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.conectar.R
import com.app.conectar.model.getdriver_list_fleet.Driver
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.session.SessionManager
import com.bumptech.glide.Glide


class DriverListAdapter(var context: Context) :
    RecyclerView.Adapter<DriverListAdapter.MyViewHolder>()  {
    var sessionManager: SessionManager? = null
    var modelList: MutableList<Driver> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.rv_driverlist, parent, false)
        sessionManager = SessionManager(context)

        return MyViewHolder(v)
    }
    fun updateList(mModelList: List<Driver>){

        modelList.clear()
        modelList.addAll(mModelList)
        notifyDataSetChanged()

    }

    fun addToList(mModelList: List<Driver>) {
        val lastIndex: Int = modelList.size
        modelList.addAll(mModelList)
        notifyItemRangeInserted(lastIndex, mModelList.size)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context)
            .load(ApiClient.BASE_URL+modelList[position].driverimage)
            .timeout(6000)
            .error(R.drawable.logo)
            .placeholder(R.drawable.logo)
            .into(holder.imgProfile)



        holder.tvName.text = modelList[position].driverName
        holder.tvPhone.text = modelList[position].driverNumber
        holder.tvLicence.text = modelList[position].driverLicence
        holder.btnView.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("driver_id",modelList[position].driverId)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_driverdetails,bundle)


        }

    }




    override fun getItemCount(): Int {
        return modelList.size
    }



    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var tvName: TextView
        var tvPhone: TextView
        var tvLicence: TextView
        var imgProfile: ImageView
        var btnView: AppCompatButton


        init {
            tvLicence = view.findViewById(R.id.tvLicence)
            tvPhone = view.findViewById(R.id.tvPhone)
            tvName = view.findViewById(R.id.tvName)
            imgProfile = view.findViewById(R.id.ivDriverImg)
            btnView = view.findViewById(R.id.btnView)


        }
    }



    init {
        this.context = context
        this.modelList = modelList
    }
}