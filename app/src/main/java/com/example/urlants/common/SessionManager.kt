package com.example.urlants.common

import android.content.Context
import android.content.SharedPreferences
import com.example.urlants.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_NAME = "user_name"
        const val EMAIL = "user_email"
        const val AVATAR_ID = "avatar_id"
    }


    fun saveAuthToken(token: String?) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun deleteUserJwt(){
        val editor = prefs.edit()
        //   if (prefs.getString(USER_NAME,null).isNullOrEmpty())
        editor.remove(USER_TOKEN).apply()
    }




    fun saveUserName(username :String?){
        val editor = prefs.edit()
        editor.putString(USER_NAME,username)
        editor.apply()
    }

    fun deleteUserName(){
        val editor = prefs.edit()
     //   if (prefs.getString(USER_NAME,null).isNullOrEmpty())
        editor.remove(USER_NAME).apply()
    }
    fun fetchUserName(): String? {

       prefs.getString(USER_NAME,null)?.let {
            return prefs.getString(USER_NAME,null)
        } ?: run {
            return ""
       }

       // return prefs.getString(USER_NAME,null)
    }

    fun saveUserEmail(email :String?){
        val editor = prefs.edit()
        editor.putString(EMAIL,email)
        editor.apply()
    }

    fun deleteUserEmail(){
        val editor = prefs.edit()

        prefs.getString(EMAIL,null)
     //   if (prefs.getString(EMAIL,null).isNullOrEmpty())
        editor.remove(EMAIL).apply()
    }
    fun fetchUserEmail(): String? {

        prefs.getString(EMAIL,null)?.let {
            return prefs.getString(EMAIL,null)
        } ?: run {
            return ""
        }
    }


    fun saveUserAvatarId(id : Int?){
        val editor = prefs.edit()
        editor.putInt(AVATAR_ID,id?:R.drawable.ic_avatarm)
        editor.apply()
    }

    fun fetchAvatarId(): Int? {

        prefs.getInt(AVATAR_ID,R.drawable.ic_avatarm)?.let {
            return prefs.getInt(AVATAR_ID,R.drawable.ic_avatarm)
        }
    }
}