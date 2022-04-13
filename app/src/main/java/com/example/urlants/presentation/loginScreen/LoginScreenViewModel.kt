package com.example.urlants.presentation.loginScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urlants.common.Resource
import com.example.urlants.common.SessionManager
import com.example.urlants.data.remote.dto.UserLoginRequest
import com.example.urlants.data.remote.dto.UserSignupRequest
import com.example.urlants.domain.usecases.get_short_url.GetShortUrlUsecase
import com.example.urlants.domain.usecases.get_url_list.GetUrlListUsecase
import com.example.urlants.domain.usecases.get_user_login_signup.GetUserLoginUsecase
import com.example.urlants.presentation.homeScreen.HomeScreenState
import com.example.urlants.presentation.signupScreen.SignupScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getUserLoginUsecase: GetUserLoginUsecase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    val TAG = "URLLOGS"

    private val _state = mutableStateOf(LoginScreenState())
    val state : State<LoginScreenState> = _state
    var username by  mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var logedIn by mutableStateOf(true)
    private lateinit var sessionManager: SessionManager

//    fun onUserdChanged(newText : String){
//       username = newText
//       // HomeScreenState(alias = newText)
//    }
//    fun onEmailChanged(newText : String){
//        email = newText
//       // HomeScreenState(alias = newText)
//    }
//    fun onPasswordChanged(newText : String){
//        password = newText
//      //  HomeScreenState(alias = newText)
//    }


    fun getUserLogin(email: String, pass: String){
        Log.d(TAG, "STated Signup")
        sessionManager = SessionManager(context = context)
        var loginReq = UserLoginRequest(email = email, password = pass)
        getUserLoginUsecase(loginReq).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = LoginScreenState(jwtToke = result.data?.jwt)
                    Log.d(TAG, result.data?.message ?: "null")
                    sessionManager.saveAuthToken(result.data?.jwt)
                    sessionManager.fetchAuthToken()?.let { Log.d(TAG, it) }
                    logedIn = true

                }
                is Resource.Error -> {
                    logedIn = false
                    _state.value = LoginScreenState(error = result.message ?: "Unexpected Error")
                    Log.d(TAG, result.message ?: "Unexpected Error")
                    Toast.makeText(context,result.message,Toast.LENGTH_LONG).show();
                }
                is Resource.Loading -> {
                    _state.value = LoginScreenState(isLoading = true)
                    _state.value = LoginScreenState(button_active = false)
                }
            }
        }.launchIn(viewModelScope)

    }


}

