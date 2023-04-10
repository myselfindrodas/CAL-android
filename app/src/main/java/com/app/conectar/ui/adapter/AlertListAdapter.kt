package com.app.conectar.ui.adapter

import android.content.Context
import android.view.*
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.conectar.R
import com.app.conectar.model.alertresponse.Notification

class AlertListAdapter(var context: Context) :
    RecyclerView.Adapter<AlertListAdapter.MyViewHolder>() {
    private val inflater: LayoutInflater
    var modelList: MutableList<Notification> = arrayListOf()
    var ctx: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = inflater.inflate(R.layout.rv_alerts, parent, false)
        return MyViewHolder(view)
    }


    fun updateList(mModelList: List<Notification>) {
        modelList.clear()
        modelList.addAll(mModelList)
        notifyDataSetChanged()

    }

    fun addToList(mModelList: List<Notification>) {
        val lastIndex: Int = modelList.size
        modelList.addAll(mModelList)
        notifyItemRangeInserted(lastIndex, mModelList.size)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvNotificationtitle.text = modelList[position].notificationTitle
        holder.tvNotificationsubtitle.text = modelList[position].notificationDescription
        holder.tvNotificationDate.text = modelList[position].updatedTs
        holder.btnAlertsdetails.setOnClickListener {

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.nav_alertdetails)
        }

    }

    override fun getItemCount(): Int {
        return modelList.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNotificationtitle: TextView
        var tvNotificationsubtitle: TextView
        var tvNotificationDate: TextView
        var btnAlertsdetails: RelativeLayout

        init {
            tvNotificationtitle = itemView.findViewById(R.id.tvNotificationtitle)
            tvNotificationsubtitle = itemView.findViewById(R.id.tvNotificationsubtitle)
            tvNotificationDate = itemView.findViewById(R.id.tvNotificationDate)
            btnAlertsdetails = itemView.findViewById(R.id.btnAlertsdetails)

        }
    }

    init {
        inflater = LayoutInflater.from(context)
        this.ctx = context
    }
}