package com.example.urlants.data.remote.dto


import com.google.gson.annotations.SerializedName

data class UserSignupResponse(
    @SerializedName("jwt")
    val jwt: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)