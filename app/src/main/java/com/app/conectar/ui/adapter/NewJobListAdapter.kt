package com.app.conectar.ui.adapter

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.conectar.R
import com.app.conectar.model.alertresponse.Notification
import com.app.conectar.model.newjobresponse.NewJobModel
import com.app.conectar.model.newjobresponse.NewJoblist
import com.app.conectar.retrofit.ApiClient
import com.app.conectar.session.SessionManager
import com.app.conectar.ui.activities.MainActivity
import com.app.conectar.ui.fragment.JoblistFragment
import com.bumptech.glide.Glide
import java.util.ArrayList

class NewJobListAdapter(var context: Context) :
    RecyclerView.Adapter<NewJobListAdapter.MyViewHolder>() {
    private val inflater: LayoutInflater
    var modelList: MutableList<NewJoblist> = arrayListOf()
    var sessionManager: SessionManager? = null
    var ctx: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = inflater.inflate(R.layout.rv_joblist, parent, false)
        sessionManager = SessionManager(ctx)
        return MyViewHolder(view)
    }


    fun updateList(mModelList: List<NewJoblist>){
        modelList.clear()
        modelList.addAll(mModelList)
        notifyDataSetChanged()

    }

    fun addToList(mModelList: List<NewJoblist>) {
        val lastIndex: Int = modelList.size
        modelList.addAll(mModelList)
        notifyItemRangeInserted(lastIndex, mModelList.size)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Glide.with(ctx)
            .load(ApiClient.BASE_URL+modelList[position].truckimage)
            .error(R.drawable.ic_truck)
            .placeholder(R.drawable.ic_truck)
            .into(holder.Imgtruck)

        holder.tvuniqueid.text = modelList[position].uniqueIds
        holder.tvDate.text = modelList[position].deliveryDate
        holder.tvpickupaddress.text = modelList[position].pickupLocationCompanyName
        holder.tvdropaddress.text = modelList[position].dropLocationCompanyName
        holder.tvEquipment.text = (modelList[position].equipmentName?:"").toString()
        holder.tvMaterial.text = modelList[position].materialName
        holder.tvTruckname.text = modelList[position].truckName


        holder.btnViewdetails.setOnClickListener {

//            val bundle = Bundle()
//            bundle.putString("truck_id",modelList[position].truckId)
//            bundle.putString("truck_type_id",modelList[position].truckType)
//            val navController = Navigation.findNavController(it)
//            navController.navigate(R.id.nav_truckdetails,bundle)


        }

    }



    override fun getItemCount(): Int {
        return modelList.size
    }



    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvuniqueid: TextView
        var tvDate: TextView
        var tvpickupaddress: TextView
        var tvdropaddress: TextView
        var tvEquipment: TextView
        var tvMaterial: TextView
        var tvTruckname: TextView
        var Imgtruck: ImageView
        var btnViewdetails: LinearLayout


        init {
            tvuniqueid = view.findViewById(R.id.tvuniqueid)
            tvDate = view.findViewById(R.id.tvDate)
            tvpickupaddress = view.findViewById(R.id.tvpickupaddress)
            tvdropaddress = view.findViewById(R.id.tvdropaddress)
            tvEquipment = view.findViewById(R.id.tvEquipment)
            tvMaterial = view.findViewById(R.id.tvMaterial)
            Imgtruck = view.findViewById(R.id.Imgtruck)
            tvTruckname = view.findViewById(R.id.tvTruckname)
            btnViewdetails = view.findViewById(R.id.btnViewdetails)


        }
    }


    init {
        inflater = LayoutInflater.from(context)
        this.ctx = context
    }
}