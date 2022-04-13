package com.example.urlants.presentation.profileScreen

import com.example.urlants.R

class ProfileScreenState (
    val isLoading: Boolean = false,
    val email: String? = "",
    var username: String? = "",
    var jwtToke : String ?= "",
    var error : String ?= "",
    var button_active : Boolean = true,
    var avatarId : Int? = R.drawable.ic_avatarm
)