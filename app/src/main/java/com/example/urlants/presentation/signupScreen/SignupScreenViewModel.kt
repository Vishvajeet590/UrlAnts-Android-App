package com.example.urlants.presentation.signupScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urlants.common.Resource
import com.example.urlants.common.SessionManager
import com.example.urlants.data.remote.dto.UserSignupRequest
import com.example.urlants.domain.usecases.get_user_login_signup.GetUserSignUpUsecase
import com.example.urlants.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SignupScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle,
    private val getUserSignupUsecase: GetUserSignUpUsecase
) : ViewModel() {
    private val _state = mutableStateOf(SignupScreenState())
    val state: State<SignupScreenState> = _state
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var logedIn by mutableStateOf(true)
    private lateinit var sessionManager: SessionManager

    val TAG = "URLLOGS"


    fun onUserChanged(newText: String) {
        username = newText
        SignupScreenState(userName = newText)
    }

    fun onEmailChanged(newText: String) {
        email = newText
        SignupScreenState(email = newText)
    }

    fun onPasswordChanged(newText: String) {
        password = newText
        SignupScreenState(password = newText)
    }


    fun getUserSignUp(username: String, email: String, pass: String) {
        Log.d(TAG, "STated Signup")
        sessionManager = SessionManager(context = context)
        sessionManager.saveUserName(username)
        var signupReq = UserSignupRequest(email, pass, username)
        getUserSignupUsecase(signupReq).onEach { result ->
            when (result) {
                is Resource.Success -> {

                    Log.d(TAG, result.data?.message ?: "null")
                    sessionManager.saveAuthToken(result.data?.jwt)
                    sessionManager.saveUserName(username = username)
                    sessionManager.saveUserEmail(email=email)
                    sessionManager.fetchAuthToken()?.let { Log.d(TAG, it) }
                    logedIn = true
                    _state.value = SignupScreenState(jwtToke = result.data?.jwt,isLogedIn = true,)
                }
                is Resource.Error -> {
                    logedIn = false
                    _state.value = SignupScreenState(error = result.message ?: "Unexpected Error",isLogedIn = false)
                    Log.d(TAG, result.message ?: "ERRROr")
                    Toast.makeText(context,result.message, Toast.LENGTH_LONG).show();

                }
                is Resource.Loading -> {
                    _state.value = SignupScreenState(isLoading = true)
                    _state.value = SignupScreenState(button_active = false)
                }
            }
        }.launchIn(viewModelScope)

    }


}

