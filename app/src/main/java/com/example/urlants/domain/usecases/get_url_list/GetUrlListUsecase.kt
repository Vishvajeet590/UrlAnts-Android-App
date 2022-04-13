package com.example.urlants.domain.usecases.get_url_list

import android.util.Log
import com.example.urlants.common.Resource
import com.example.urlants.data.remote.dto.UrlListResponse
import com.example.urlants.data.remote.dto.UserLoginResponse
import com.example.urlants.domain.repository.UrlRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUrlListUsecase @Inject constructor(private val repository: UrlRepository)  {
    operator fun invoke(jwtToken : String) : Flow<Resource<UrlListResponse>> = flow {
        val TAG = "URLLOGS"
        try {
            emit(Resource.Loading<UrlListResponse>())
            Log.d("URLLOGS", "Jwt = $jwtToken")
            emit(Resource.Loading<UrlListResponse>())

            val urlList = repository.getUrlList(jwtToken)
            emit(Resource.Success<UrlListResponse>(urlList))
        }catch (e : HttpException){
            if (e.code() == 401){
                Log.d(TAG,"JWT Expired. Login again")
                emit(Resource.Error<UrlListResponse>("expire"))
            }
            else if (e.code() == 500){
                Log.d(TAG,"Url list is empty")
                emit(Resource.Error<UrlListResponse>("empty"))
            }
            else{
                Log.d(TAG,"CHeck your Internet Connection")
                emit(Resource.Error<UrlListResponse>("Check your Internet Connection"))
            }
        }catch (e : IOException){
            emit(Resource.Error<UrlListResponse>("Check Internet Connection"))
        }
    }
}