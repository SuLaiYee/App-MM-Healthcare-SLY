package com.padcmyanmar.myapplication.data.vos

import com.google.gson.annotations.SerializedName

class HealthCareVO(@SerializedName("id") var id:Int = 0,
                   @SerializedName("title") var title: String="",
                   @SerializedName("image") var image: String="",
                   @SerializedName("author") var author: AuthorVO?= null,
                   @SerializedName("short-description") var shortDescription: String ="",
                   @SerializedName("published-date") var publishedDate: String="",
                   @SerializedName("complete-url") var completeUrl: String="",
                   @SerializedName("info-type") var article: String="")
{
}