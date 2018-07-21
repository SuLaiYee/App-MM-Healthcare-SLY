package com.padcmyanmar.myapplication.events

import com.padcmyanmar.myapplication.data.vos.HealthCareVO

class SuccessEvent
{
    class HealthsLoadedEvent(val loadedHealths: List<HealthCareVO>)

    class EmptyDataLoadedEvent(val errorMsg: String? ="Empty Response")
}