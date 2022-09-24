package com.leander.momo_practice.house_info.view.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leander.momo_practice.R
import com.leander.momo_practice.global.network.response.HouseInfoResponse


/* Created on 2022/9/23 */

class InfoListAdapter(
    private val layout: Int,
    private val dataset : List<HouseInfoResponse.Result.Result>,
    private var onItemClick: ((HouseInfoResponse.Result.Result) -> Unit)? = null
) : RecyclerView.Adapter<InfoListAdapter.InfoListViewHolder>() {


    inner class InfoListViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        var imgHouseImg: ImageView?=null
        var tClosed: TextView?=null
        var tTitle: TextView?=null
        var tContent: TextView?=null

        init {
            imgHouseImg  = itemView.findViewById(R.id.imgHouseImg)
            tTitle = itemView.findViewById(R.id.tTitle)
            tClosed = itemView.findViewById(R.id.tClosed)
            tContent = itemView.findViewById(R.id.tContent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoListViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return InfoListViewHolder(adapterLayout!!)
    }

    override fun onBindViewHolder(holder: InfoListViewHolder, position: Int) {
        setData(holder, dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    private fun setData(holder: InfoListViewHolder, info: HouseInfoResponse.Result.Result) {
        val contex = holder.itemView.context
        holder.tTitle?.text = info.eName
        holder.tContent?.text = info.eInfo
        holder.tClosed?.text = info.eMemo.ifEmpty { contex.getString(R.string.no_closed_info) }

        holder.imgHouseImg?.let {
            Glide.with(holder.itemView.context)
                .load(info.ePicUrl)
                .into(it)
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(info)
        }
    }

}