package com.example.urlants.data.repository

import com.example.urlants.data.remote.UrlantsApi
import com.example.urlants.data.remote.dto.*
import com.example.urlants.domain.repository.UrlRepository
import javax.inject.Inject

class UrlRepositoryImp @Inject constructor(
    private val api : UrlantsApi
) : UrlRepository{
    override suspend fun getShortUrl(jwtToken: String,req : ShortUrlRequest): ShortUrlResponse {
        return api.getShortUrl(jwtToken,req)
    }

    override suspend fun getUrlList(jwtToken: String): UrlListResponse {
        return api.getShortUrlList(jwtToken)
    }


    override suspend fun getUrlStats(keyVal: String): UrlStatResponse {
        return api.getUrlStats(keyVal)
    }

    override suspend fun getUserSignUp(req: UserSignupRequest): UserSignupResponse {
        return api.getUserSignup(req)
    }

    override suspend fun getUserLogin(req: UserLoginRequest): UserLoginResponse {
        return api.getUserLogin(req)
    }

    override suspend fun deleteUrl(jwtToken : String,req: DeleteUrlRequest): DeletUrlResponse {
        return api.deteteShortUrl(jwtToken,req)
    }

}