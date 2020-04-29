package com.danusuhendra.mymvp2.repository

import com.danusuhendra.mymvp2.database.UsersDao
import com.danusuhendra.mymvp2.model.Users

class UsersRepository(private val  usersDao: UsersDao) {
    suspend fun registerUsers(users: Users){
        usersDao.register(users)
    }

    suspend fun loginUsers(users: Users, onLogin : (Users)->Unit) {
        val result = usersDao.login(users.username, users.password)
        onLogin(result)
    }
}