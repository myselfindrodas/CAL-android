package com.app.conectar.ui.adapter

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.conectar.R
import com.app.conectar.model.alertresponse.Notification
import com.app.conectar.model.appintrolistresponse.AppIntro
import com.app.conectar.retrofit.ApiClient
import com.bumptech.glide.Glide

class InfoGraphicListAdapter(var context: Context) :
    RecyclerView.Adapter<InfoGraphicListAdapter.MyViewHolder>() {
    private val inflater: LayoutInflater
    var modelList: MutableList<AppIntro> = arrayListOf()
    var ctx: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = inflater.inflate(R.layout.rv_infographic, parent, false)
        return MyViewHolder(view)
    }


    fun updateList(mModelList: List<AppIntro>) {
        modelList.clear()
        modelList.addAll(mModelList)
        notifyDataSetChanged()

    }

    fun addToList(mModelList: List<AppIntro>) {
        val lastIndex: Int = modelList.size
        modelList.addAll(mModelList)
        notifyItemRangeInserted(lastIndex, mModelList.size)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Glide.with(context)
            .load(modelList[position].image)
            .timeout(6000)
            .error(R.drawable.story1)
            .placeholder(R.drawable.story1)
            .into(holder.rvImage)

        holder.tvTitle.text = modelList[position].description


    }

    override fun getItemCount(): Int {
        return modelList.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rvImage: ImageView
        var tvTitle: TextView

        init {
            rvImage = itemView.findViewById(R.id.rvImage)
            tvTitle = itemView.findViewById(R.id.tvTitle)

        }
    }

    init {
        inflater = LayoutInflater.from(context)
        this.ctx = context
    }
}