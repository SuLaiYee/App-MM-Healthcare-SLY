package com.padcmyanmar.myapplication.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.padcmyanmar.myapplication.viewholders.BaseViewHolder
import java.util.ArrayList

abstract class BaseRecyclerAdapter<T, W>(context: Context) : RecyclerView.Adapter<BaseViewHolder<W>>() {

    private var healthData: MutableList<W>? = null
    protected var healthLayoutInflater: LayoutInflater

    val items: List<W>
        get() {
            val data = healthData
            return data ?: ArrayList()
        }

    init {
        healthData = ArrayList()
        healthLayoutInflater = LayoutInflater.from(context)
    }
    override fun onBindViewHolder(holder: BaseViewHolder<W>, position: Int) {
        holder.bindData(healthData!![position])
    }

    override fun getItemCount(): Int {
        return healthData!!.size
    }

    fun setHealthData(healthsData: MutableList<W>) {
        healthData = healthsData
        notifyDataSetChanged()
    }
    fun appendHealthsData(healthsData: List<W>) {
        healthData!!.addAll(healthsData)
        notifyDataSetChanged()
    }




}