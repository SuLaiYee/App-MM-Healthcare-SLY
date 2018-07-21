package com.padcmyanmar.myapplication.network

import com.padcmyanmar.myapplication.network.responses.GetHealthsResponse
import com.padcmyanmar.myapplication.utils.AppConstants
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MMHealthCareApi {

    @FormUrlEncoded
    @POST(AppConstants.API_GET_HEALTH_CARE_INFO)
    fun loadHealthCareInfo(
            @Field("access_token") accessToken: String) : Call<GetHealthsResponse>
}