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
import com.app.conectar.model.gettruck_list_fleet.Truck
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.session.SessionManager
import com.bumptech.glide.Glide


class TruckListAdapter(var context: Context) :
    RecyclerView.Adapter<TruckListAdapter.MyViewHolder>()  {
    var sessionManager: SessionManager? = null
    var modelList: MutableList<Truck> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.rv_trucklist, parent, false)
        sessionManager = SessionManager(context)

        return MyViewHolder(v)
    }
    fun updateList(mModelList: List<Truck>){
        modelList.clear()
        modelList.addAll(mModelList)
        notifyDataSetChanged()

    }

    fun addToList(mModelList: List<Truck>) {
        val lastIndex: Int = modelList.size
        modelList.addAll(mModelList)
        notifyItemRangeInserted(lastIndex, mModelList.size)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context)
            .load(ApiClient.BASE_URL+modelList[position].truckimage)
            .timeout(6000)
            .error(R.drawable.logo)
            .placeholder(R.drawable.logo)
            .into(holder.ivTruckImg)



        holder.tvTruckModel.text = modelList[position].truckModel
        holder.tvTruckNo.text = modelList[position].truckRegNumber
        holder.tvTruckType.text = modelList[position].truckTypeName
        holder.btnView.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("truck_id",modelList[position].truckId)
            bundle.putString("truck_type_id",modelList[position].truckType)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_truckdetails,bundle)


        }

    }




    override fun getItemCount(): Int {
        return modelList.size
    }



    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var tvTruckModel: TextView
        var tvTruckNo: TextView
        var tvTruckType: TextView
        var ivTruckImg: ImageView
        var btnView: AppCompatButton


        init {
            tvTruckType = view.findViewById(R.id.tvTruckType)
            tvTruckNo = view.findViewById(R.id.tvTruckNo)
            tvTruckModel = view.findViewById(R.id.tvTruckModel)
            ivTruckImg = view.findViewById(R.id.ivTruckImg)
            btnView = view.findViewById(R.id.btnView)


        }
    }



    init {
        this.context = context
        this.modelList = modelList
    }
}