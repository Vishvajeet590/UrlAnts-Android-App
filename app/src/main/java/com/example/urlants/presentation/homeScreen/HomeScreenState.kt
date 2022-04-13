package com.example.urlants.presentation.homeScreen

import com.example.urlants.data.remote.dto.ShortUrlRequest
import com.example.urlants.data.remote.dto.ShortUrlResponse
import com.example.urlants.data.remote.dto.Statl
import com.example.urlants.data.remote.dto.UrlListResponse
import com.example.urlants.presentation.Screen

class HomeScreenState(
    val isLoading: Boolean = false,
    val shortUrl: ShortUrlResponse ?= null,
    val error: String = "",
    var longUrl: String = "",
    var alias: String = "",
    var isJwt: Boolean = false,
    var username: String = "Hello,",
    var noListStatement: String = "Login/Sign Up to view your URLs",
    var showList: Boolean = false,
    var urlList: List<Statl> ?= null,
    var showCard : Boolean = false,
    var showEmptyImage :Boolean = true,
    var urlItem : Statl?= null,
    var shortBtnEnable : Boolean = true,
    var isLogedIn : Boolean = false,
    var login_profile_nav : String? = ""

)