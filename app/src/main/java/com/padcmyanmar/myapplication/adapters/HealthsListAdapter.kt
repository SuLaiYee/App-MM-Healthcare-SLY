package com.padcmyanmar.myapplication.adapters

import android.content.Context
import android.view.ViewGroup
import com.padcmyanmar.myapplication.R
import com.padcmyanmar.myapplication.data.vos.HealthCareVO
import com.padcmyanmar.myapplication.deglates.HealthsDelegate
import com.padcmyanmar.myapplication.viewholders.HealthCareViewHolder

class HealthsListAdapter(context: Context, private val healthCareDelegate: HealthsDelegate) : BaseRecyclerAdapter <HealthCareViewHolder, HealthCareVO>(context){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthCareViewHolder {
        val healthCareItemView = healthLayoutInflater.inflate(R.layout.view_holder_mm_health,parent,false)
        return HealthCareViewHolder(healthCareItemView, healthCareDelegate)

    }

}
