package com.example.urlants.presentation.loginScreen

class LoginScreenState(
    val isLoading: Boolean = false,
    val email: String = "",
    var password: String = "",
    var jwtToke : String ?= "",
    var error : String ?= "",
    var button_active : Boolean = true,
   // var isLogedIn : Boolean? = false,
)