package com.example.urlants.data.remote

import com.example.urlants.data.remote.dto.*
import retrofit2.http.*

interface UrlantsApi {

    @Headers("Content-Type: application/json")
    @POST("/v1/short")
    suspend fun getShortUrl(@Header("authorization") jwtToke : String,@Body shortUrl : ShortUrlRequest) : ShortUrlResponse

    @Headers("Content-Type: application/json")
    @GET("/v1/list")
    suspend fun getShortUrlList(@Header("authorization") jwtToke : String) : UrlListResponse

    @Headers("Content-Type: application/json")
    @POST("/v1/delete")
    suspend fun deteteShortUrl(@Header("authorization") jwtToke : String, @Body deleteReq : DeleteUrlRequest): DeletUrlResponse

    @Headers("Content-Type: application/json")
    @POST("/v1/login")
    suspend fun getUserLogin(@Body loginReq : UserLoginRequest) : UserLoginResponse

    @Headers("Content-Type: application/json")
    @POST("/v1/signup")
    suspend fun getUserSignup(@Body signupReq : UserSignupRequest) : UserSignupResponse

    @Headers("Content-Type: application/json")
    @GET("/v1/stat/{keyVal}")
    suspend fun getUrlStats(@Path("keyVal") keyVal : String) : UrlStatResponse



}