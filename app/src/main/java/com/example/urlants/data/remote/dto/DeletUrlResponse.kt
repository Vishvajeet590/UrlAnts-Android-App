package com.example.urlants.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DeletUrlResponse(
    @SerializedName("success")
    val success: Boolean
)