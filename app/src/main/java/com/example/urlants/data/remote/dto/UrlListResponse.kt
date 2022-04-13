package com.example.urlants.data.remote.dto


import com.google.gson.annotations.SerializedName

data class UrlListResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("statlList")
    val statlList: List<Statl>,
    @SerializedName("success")
    val success: Boolean
)