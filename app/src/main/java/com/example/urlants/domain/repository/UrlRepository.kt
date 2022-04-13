package com.example.urlants.domain.repository

import com.example.urlants.data.remote.dto.*

interface UrlRepository {
    suspend fun getShortUrl(jwtToken: String,req : ShortUrlRequest): ShortUrlResponse
    suspend fun getUrlList(jwtToken : String) : UrlListResponse
    suspend fun getUrlStats(keyVal : String) : UrlStatResponse
    suspend fun getUserSignUp(req : UserSignupRequest) : UserSignupResponse
    suspend fun getUserLogin(req : UserLoginRequest) : UserLoginResponse
    suspend fun deleteUrl(jwtToken : String,req : DeleteUrlRequest) : DeletUrlResponse
}