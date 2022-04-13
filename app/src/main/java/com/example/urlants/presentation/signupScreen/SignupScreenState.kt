package com.example.urlants.presentation.signupScreen

import com.example.urlants.data.remote.dto.ShortUrlResponse
import com.example.urlants.data.remote.dto.UrlListResponse
import com.example.urlants.presentation.Screen

class SignupScreenState(
    val isLoading: Boolean = false,
    val email: String = "",
    var password: String = "",
    var userName: String = "",
    var jwtToke : String ?= "",
    var error : String ?= "",
    var button_active : Boolean = true,
    var isLogedIn : Boolean? = false,
    var profile_nav : String? =Screen.ProfileScreen.route

)