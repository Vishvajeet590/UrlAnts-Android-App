package com.example.urlants.domain.usecases.get_user_login_signup

import android.util.Log
import com.example.urlants.common.Resource
import com.example.urlants.data.remote.dto.UserLoginRequest
import com.example.urlants.data.remote.dto.UserLoginResponse
import com.example.urlants.data.remote.dto.UserSignupResponse
import com.example.urlants.domain.repository.UrlRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserLoginUsecase @Inject constructor(private val repository: UrlRepository) {
    operator fun invoke(logInRequest : UserLoginRequest) : Flow<Resource<UserLoginResponse>> = flow {
       val TAG = "URLLOGS"
        try {
            emit(Resource.Loading<UserLoginResponse>())
            var loginResp = repository.getUserLogin(logInRequest)
            Log.d(TAG,loginResp.message)
            emit(Resource.Success<UserLoginResponse>(loginResp))
        } catch (e : IOException){
            Log.d(TAG,"ERROR")
            emit(Resource.Error<UserLoginResponse>("Something went wrong"))
        }catch (e : HttpException){
            if (e.code() == 500){
                Log.d(TAG,"Check your credentials")
                emit(Resource.Error<UserLoginResponse>("Check your credentials"))
            }else{
                Log.d(TAG,"CHeck your Internet Connection")
                emit(Resource.Error<UserLoginResponse>("Check your Internet Connection"))
            }
        }
    }
}