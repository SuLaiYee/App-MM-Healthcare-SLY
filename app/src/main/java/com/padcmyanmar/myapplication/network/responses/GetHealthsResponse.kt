package com.padcmyanmar.myapplication.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.myapplication.data.vos.HealthCareVO

class GetHealthsResponse
{
    @SerializedName("code")
    private val code: Int = 0

    @SerializedName("message")
    private val message: String? =null

    @SerializedName("healthcare-info")
    private var healthCareList: List<HealthCareVO>? = null

    fun getCode() : Int {
        return code
    }

    fun getMessage() : String? {
        return message
    }

    fun getHealthsList(): List<HealthCareVO>
    {
        if(healthCareList == null)
        {
            healthCareList = ArrayList<HealthCareVO>()
        }
        val healthsList = healthCareList
        return healthsList!!
    }
}