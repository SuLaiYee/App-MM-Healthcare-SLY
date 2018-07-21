package com.padcmyanmar.myapplication.data.vos

import com.google.gson.annotations.SerializedName

class AuthorVO(@SerializedName("author-id") var authorId: Int = 0 ,
               @SerializedName("author-name") var authorName: String="",
               @SerializedName("author-picture") var authorPhoto: String=""
                )
{
}