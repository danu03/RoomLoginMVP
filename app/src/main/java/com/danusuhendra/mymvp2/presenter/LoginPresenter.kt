package com.danusuhendra.mymvp2.presenter
import com.danusuhendra.mymvp2.model.Users
import com.danusuhendra.mymvp2.repository.UsersRepository
import com.danusuhendra.mymvp2.view.login.LoginView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter(private val repository: UsersRepository, private val view : LoginView){
    fun loginUsers(users: Users) = GlobalScope.launch(Dispatchers.Main) {
        repository.loginUsers(users){
            if (it!= null){
                view.loginResult(true)
                view.showMessage("Login Success")
                view.showUsers(it)
            }else{
                view.loginResult(false)
                view.showMessage("Login Failed")
            }
        }
    }
}