package com.danusuhendra.mymvp2.view.login

import com.danusuhendra.mymvp2.model.Users

interface LoginView {
    fun showUsers(users: Users)
    fun loginResult(status : Boolean)
    fun showMessage(data : String)
}