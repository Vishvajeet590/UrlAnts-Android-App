package com.example.urlants.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DeleteUrlRequest(
    @SerializedName("ownerid")
    val ownerid: Int,
    @SerializedName("shortUrl")
    val shortUrl: String
)