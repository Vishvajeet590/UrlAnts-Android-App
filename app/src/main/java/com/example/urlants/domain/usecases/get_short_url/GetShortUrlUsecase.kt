package com.example.urlants.domain.usecases.get_short_url

import android.util.Log
import com.example.urlants.common.Resource
import com.example.urlants.data.remote.dto.ShortUrlResponse
import com.example.urlants.data.remote.dto.ShortUrlRequest
import com.example.urlants.domain.repository.UrlRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetShortUrlUsecase @Inject constructor(private val repository: UrlRepository) {
    operator fun invoke(jwtToken : String,shortUrlRequest : ShortUrlRequest) : Flow<Resource<ShortUrlResponse>> = flow {
        try {
            Log.d("URLLOGS",shortUrlRequest.originalUrl)
            emit(Resource.Loading<ShortUrlResponse>())
            val shortUrl = repository.getShortUrl(jwtToken,shortUrlRequest)
            emit(Resource.Success<ShortUrlResponse>(shortUrl))
        }catch (e : HttpException){
            emit(Resource.Error<ShortUrlResponse>(e.localizedMessage ?: "Unexpected error"))
        }catch (e : IOException){
            emit(Resource.Error<ShortUrlResponse>("Check Internet Connection"))
        }
    }
}