package com.example.urlants.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ShortUrlResponse(
    @SerializedName("shortUrlKey")
    val shortUrlKey: String,
    @SerializedName("success")
    val success: Boolean
)