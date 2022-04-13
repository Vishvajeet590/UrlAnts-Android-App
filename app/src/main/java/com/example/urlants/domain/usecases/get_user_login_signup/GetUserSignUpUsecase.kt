package com.example.urlants.domain.usecases.get_user_login_signup

import android.util.Log
import com.example.urlants.common.Resource
import com.example.urlants.data.remote.dto.UserSignupRequest
import com.example.urlants.data.remote.dto.UserSignupResponse
import com.example.urlants.domain.repository.UrlRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserSignUpUsecase @Inject constructor(private val repository: UrlRepository) {
    operator fun invoke(signUpRequest : UserSignupRequest) : Flow<Resource<UserSignupResponse>> = flow {

        try {
            emit(Resource.Loading<UserSignupResponse>())
            var signupResp = repository.getUserSignUp(signUpRequest)
            emit(Resource.Success<UserSignupResponse>(signupResp))
        }catch (e : IOException){
            Log.d("URLLOGS","ERROR")
            emit(Resource.Error<UserSignupResponse>("Something went wrong"))
        }catch (e : HttpException){
            Log.d("URLLOGS","Check your Internet Connection")
            emit(Resource.Error<UserSignupResponse>("Check your Internet Connection"))
        }
    }
}