package com.danusuhendra.mymvp2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danusuhendra.mymvp2.model.Users

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun register(users: Users)

    @Query("SELECT * FROM users_table WHERE username = :username AND password = :password")
    suspend fun login(username : String?, password : String?) : Users
}