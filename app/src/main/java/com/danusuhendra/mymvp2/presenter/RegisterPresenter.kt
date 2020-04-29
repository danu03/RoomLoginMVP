package com.danusuhendra.mymvp2.presenter

import com.danusuhendra.mymvp2.model.Users
import com.danusuhendra.mymvp2.repository.UsersRepository
import com.danusuhendra.mymvp2.view.register.RegisterView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterPresenter(private val repository: UsersRepository, private val view: RegisterView) {
    fun registerUsers(users: Users) = GlobalScope.launch(Dispatchers.Main) {
        repository.registerUsers(users)
        view.showRegister(true)
        view.showMessage("Register Success!")
    }
}