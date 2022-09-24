package com.leander.momo_practice.house_info.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leander.momo_practice.R
import com.leander.momo_practice.global.network.response.AnimalInfoResponse

/* Created on 2022/9/23 */

class MoreInfoListAdapter(
    private val layout: Int,
    private val dataset : List<AnimalInfoResponse.Result.Result>,
    private var onItemClick: ((AnimalInfoResponse.Result.Result) -> Unit)? = null
) : RecyclerView.Adapter<MoreInfoListAdapter.MoreInfoListViewHolder>() {

    inner class MoreInfoListViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreInfoListViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MoreInfoListViewHolder(adapterLayout!!)
    }

    override fun onBindViewHolder(holder: MoreInfoListViewHolder, position: Int) {
        setData(holder, dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    private fun setData(holder: MoreInfoListViewHolder, info: AnimalInfoResponse.Result.Result) {
        holder.tTitle?.text = info.aNameCh
        holder.tContent?.text = info.aDistribution
        holder.tClosed?.visibility = View.GONE

        holder.imgHouseImg?.let {
            Glide.with(holder.itemView.context)
                .load(info.aPic01Url)
                .into(it)
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(info)
        }
    }
}