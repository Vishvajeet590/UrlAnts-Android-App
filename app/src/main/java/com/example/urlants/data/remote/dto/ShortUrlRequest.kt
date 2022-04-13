package com.example.urlants.data.remote.dto


import com.google.gson.annotations.SerializedName

data class  ShortUrlRequest(
    @SerializedName("customAlias")
    val customAlias: String,
    @SerializedName("originalUrl")
    val originalUrl: String
)

