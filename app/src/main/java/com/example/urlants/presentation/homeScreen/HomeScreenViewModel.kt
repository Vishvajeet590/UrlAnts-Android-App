package com.example.urlants.presentation.homeScreen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urlants.common.Resource
import com.example.urlants.common.SessionManager
import com.example.urlants.data.remote.dto.ShortUrlRequest
import com.example.urlants.data.remote.dto.Statl
import com.example.urlants.data.remote.dto.UrlListResponse
import com.example.urlants.domain.usecases.get_short_url.GetShortUrlUsecase
import com.example.urlants.domain.usecases.get_url_list.GetUrlListUsecase
import com.example.urlants.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getShortUrlUsecase: GetShortUrlUsecase,
    private val getUrlListUsecase: GetUrlListUsecase,
    private val sessionManager : SessionManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state
    var longUrl by mutableStateOf("")
    var alias by mutableStateOf("")
    var userName by mutableStateOf("")
    var profile_login_nav by mutableStateOf(Screen.LoginScreen.route)
    //val sessionManager = SessionManager(context)
    var TAG = "HomeScreenViewModel"
    val currentTime: String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

     //TODO
     // var testToken =
     //   "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoidXNlciIsIklkIjo5LCJleHAiOjE2NDk1MTUyODJ9.XC8_Oej_gGozdriQ2wvFOf8NfoTxHxFUnT0UWwoOf3M"

    init {
        Log.d(TAG, "INIT OF HOMESCREEN VM")
        Log.d(TAG, "Checking for JWT token")

       // checkLogedIn()
    }


     fun getUrlList(jwtToken: String) {
        Log.d("TAG", "INSIDE GET LIST")
        getUrlListUsecase(jwtToken).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HomeScreenState(urlList = result.data?.statlList, showList = true,showEmptyImage = false)
                    //    Log.d("URLLOGS", result.data?.statlList?.get(0)?.longUrl ?: "Nothining")

                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(error = result.message ?: "Unexpected Error")
                    if (result.message.equals("empty")) {
                        Log.d("URLLOGS", result.message ?: "Unexpected Error")
                        _state.value.noListStatement = "Your list is empty."
                    } else {
                        _state.value = HomeScreenState(error = result.message ?: "Unexpected Error")
                        Log.d("URLLOGS", result.message ?: "Unexpected Error")
                    }

                }
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



    fun getShortUrl(longUrl: String, alias: String) {

        if (longUrl.isNotEmpty()) {
            Log.d("URLLOGS","URL =  $longUrl   $alias")
        var shortReq = ShortUrlRequest(originalUrl = longUrl,customAlias = alias)
            Log.d("URLLOGS","REQ =  ${shortReq.originalUrl}   $alias")
        getShortUrlUsecase( sessionManager.fetchAuthToken()?:"" ,shortReq).onEach { result ->
            when (result) {
                is Resource.Success -> {

                    _state.value = HomeScreenState(shortUrl = result.data)
                    Log.d("URLLOGS", result.data?.shortUrlKey ?: "Nothining")
                    sessionManager.fetchAuthToken()?.let { getUrlList(it) } ?: run{
                        var shortItem = Statl(
                            createdAt = currentTime,
                            longUrl=longUrl,
                            ownerId = -999,
                            message = "",
                            redirects = 0,
                            shortUrl = result.data?.shortUrlKey ?: "",
                            success = true
                        )
                        _state.value = HomeScreenState(showCard = true,urlItem = shortItem,showEmptyImage = false)
                    }
                }
                is Resource.Error -> {
                    if (result.message.equals("empty")) {
                        Log.d("URLLOGS", result.message ?: "Unexpected Error")
                    } else if (result.message.equals("expire")) {
                        sessionManager.deleteUserName()
                        Log.d("URLLOGS", result.message ?: "Unexpected Error")
                    } else {
                        _state.value = HomeScreenState(error = result.message ?: "Unexpected Error")
                        Log.d("URLLOGS", result.message ?: "Unexpected Error")
                    }
                }
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true,shortBtnEnable = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}


     fun checkJWT() {

        sessionManager.fetchAuthToken()?.let {
            Log.d(TAG, "JWT FOUND = $it")
            _state.value = HomeScreenState(isJwt = true)
            getUrlList(it)
          //  checkLogedIn()
        }

        sessionManager.fetchUserName()?.let {
            Log.d(TAG, "Username Found = $it")
            _state.value = HomeScreenState(username = it)
        }

    }


    fun onTextChanged(newText: String) {
        longUrl = newText
        HomeScreenState(longUrl = newText)
    }

    fun onAliasChanged(newText: String) {
        alias = newText
        HomeScreenState(alias = newText)
    }


    fun copyUrl(url : String){
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip : ClipData = ClipData.newPlainText("url",url)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context,"Copied...",Toast.LENGTH_SHORT).show()
    }


     fun checkLogedIn(){

         Log.d("HOME_SCREEN","Setting route to Profile")

        var user = sessionManager.fetchUserName()
        var jwt = sessionManager.fetchAuthToken()
        if (user.isNullOrEmpty() || jwt.isNullOrEmpty()){
            Log.d(TAG,"Setting route to Login")
            _state.value = HomeScreenState(login_profile_nav = Screen.LoginScreen.route)
            profile_login_nav = Screen.LoginScreen.route

        }
        else {
            Log.d(TAG,"Setting route to Profile")
            profile_login_nav = Screen.ProfileScreen.route
            userName = user
            _state.value = HomeScreenState(login_profile_nav = Screen.ProfileScreen.route)
        }
    }

}