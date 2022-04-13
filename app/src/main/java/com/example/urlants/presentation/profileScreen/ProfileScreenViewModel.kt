package com.example.urlants.presentation.profileScreen

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.urlants.R
import com.example.urlants.common.Constants
import com.example.urlants.common.SessionManager
import com.example.urlants.presentation.loginScreen.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sessionManager :SessionManager

) : ViewModel() {

    private val _state = mutableStateOf(ProfileScreenState())
    val state : State<ProfileScreenState> = _state
    //private lateinit var sessionManager: SessionManager
    var logedIn by mutableStateOf(true)

    init {
       // sessionManager = SessionManager(context = context)
        _state.value = ProfileScreenState(username = sessionManager.fetchUserName(),email = sessionManager.fetchUserEmail(),avatarId = sessionManager.fetchAvatarId())

    }

    fun logOutUser(){
        sessionManager.deleteUserName()
        sessionManager.deleteUserEmail()
        sessionManager.deleteUserJwt()
        logedIn = false
    }

    fun switchAvatar(id : Int){
        if (id == R.drawable.ic_avatarm){
            _state.value = ProfileScreenState(username = sessionManager.fetchUserName(),email = sessionManager.fetchUserEmail(),avatarId = R.drawable.ic_avatarf)
            sessionManager.saveUserAvatarId(R.drawable.ic_avatarf)
        }
        else{
            _state.value = ProfileScreenState(username = sessionManager.fetchUserName(),email = sessionManager.fetchUserEmail(),avatarId = R.drawable.ic_avatarm)
            sessionManager.saveUserAvatarId(R.drawable.ic_avatarm)
        }
    }

}