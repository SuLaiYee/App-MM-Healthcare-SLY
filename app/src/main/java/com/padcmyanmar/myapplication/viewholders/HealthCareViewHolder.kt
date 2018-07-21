package com.padcmyanmar.myapplication.viewholders

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import com.padcmyanmar.myapplication.data.vos.HealthCareVO
import com.padcmyanmar.myapplication.deglates.HealthsDelegate
import kotlinx.android.synthetic.main.view_holder_mm_health.view.*

class HealthCareViewHolder(itemView: View, private val mDelegate: HealthsDelegate) : BaseViewHolder<HealthCareVO>(itemView) {

    @SuppressLint("SetTextI18n")
    override fun bindData(data: HealthCareVO) {

        healthCareData = data
        itemView.tv_health_heading.text = healthCareData!!.title
        itemView.tv_type.text = if (healthCareData!!.article != "") {
            healthCareData!!.article
        } else {
            "Other"
        }

        itemView.tv_publisher.text = healthCareData!!.author!!.authorName
        if(data.image!=null) {
            Glide.with(itemView.iv_health_photo)
                    .load(data!!.image)
                    .into(itemView.iv_health_photo)
        }

    }

    override fun onClick(v: View?) {
        mDelegate.onTapNews(healthCareData)
    }


}