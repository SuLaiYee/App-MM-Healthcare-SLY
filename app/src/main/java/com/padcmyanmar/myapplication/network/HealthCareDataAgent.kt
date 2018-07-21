package com.padcmyanmar.myapplication.network

import com.google.gson.Gson
import com.padcmyanmar.myapplication.events.ErrorEvent
import com.padcmyanmar.myapplication.events.SuccessEvent
import com.padcmyanmar.myapplication.network.responses.GetHealthsResponse
import com.padcmyanmar.myapplication.utils.AppConstants
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HealthCareDataAgent {

    companion object {
        private var INSTANCE: HealthCareDataAgent? = null
        fun getInstance(): HealthCareDataAgent {
            if (INSTANCE == null) {
                INSTANCE = HealthCareDataAgent()
            }

            val i = INSTANCE
            return i!!
        }
    }

    private val mHealthsApi: MMHealthCareApi

    private constructor() {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()

        mHealthsApi = retrofit.create(MMHealthCareApi::class.java)
    }

    fun loadHealthsCare(accessToken: String) {
        val healthsResponseCall = mHealthsApi.loadHealthCareInfo(accessToken)
        healthsResponseCall.enqueue(object : Callback<GetHealthsResponse> {
            override fun onFailure(call: Call<GetHealthsResponse>?, t: Throwable?) {
                EventBus.getDefault().post(ErrorEvent.ApiErrorEvent(t))
            }

            override fun onResponse(call: Call<GetHealthsResponse>, response: Response<GetHealthsResponse>) {
                val healthsResponse: GetHealthsResponse? = response.body()
                if (healthsResponse != null
                        && healthsResponse.getCode() == 200
                        && healthsResponse.getHealthsList().isNotEmpty()) {
                    val healthsLoadedEvent = SuccessEvent.HealthsLoadedEvent( healthsResponse.getHealthsList())
                    EventBus.getDefault().post(healthsLoadedEvent)
                } else {
                    if(healthsResponse != null)
                        EventBus.getDefault().post(SuccessEvent.EmptyDataLoadedEvent(healthsResponse.getMessage()))
                    else
                        EventBus.getDefault().post(SuccessEvent.EmptyDataLoadedEvent())
                }
            }
        })
    }
}