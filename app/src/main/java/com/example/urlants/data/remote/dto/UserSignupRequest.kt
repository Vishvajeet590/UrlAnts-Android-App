package com.example.urlants.data.remote.dto


import com.google.gson.annotations.SerializedName

data class UserSignupRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)