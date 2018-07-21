package com.padcmyanmar.myapplication.data.models

import com.padcmyanmar.myapplication.data.vos.HealthCareVO
import com.padcmyanmar.myapplication.events.SuccessEvent
import com.padcmyanmar.myapplication.network.HealthCareDataAgent
import com.padcmyanmar.myapplication.utils.AppConstants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HealthCareModel
{
    companion object {
            private var INSTANCE: HealthCareModel? = null
            fun getInstance(): HealthCareModel {
                if (INSTANCE == null) {
                    INSTANCE = HealthCareModel()
                }

                val i = INSTANCE
                return i!!
            }
    }

    private constructor() {
        EventBus.getDefault().register(this)
    }

    private var healthCarePage: Int = 1
    private var healthCareDate: HashMap<Int,HealthCareVO> = HashMap()

    fun loadHealths(){
        HealthCareDataAgent.getInstance().loadHealthsCare(AppConstants.PARAM_ACCESS_TOKEN)
    }

    fun forceLoadHealths() {
        healthCarePage = 1
        healthCareDate = HashMap()
        HealthCareDataAgent.getInstance().loadHealthsCare(AppConstants.PARAM_ACCESS_TOKEN)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onNewsLoadedEvent(healthsLoadedEvent: SuccessEvent.HealthsLoadedEvent) {
        for (healths: HealthCareVO in healthsLoadedEvent.loadedHealths) {
            healthCareDate[healths.id] = healths
        }

    }

}