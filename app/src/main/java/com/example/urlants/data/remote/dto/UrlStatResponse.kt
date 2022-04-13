package com.example.urlants.data.remote.dto


import com.google.gson.annotations.SerializedName

data class UrlStatResponse(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("longUrl")
    val longUrl: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("ownerId")
    val ownerId: Int,
    @SerializedName("redirects")
    val redirects: Int,
    @SerializedName("shortUrl")
    val shortUrl: String,
    @SerializedName("success")
    val success: Boolean
)