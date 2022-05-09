package com.tuly.userdatabase.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(item: List<UserEntity>)

    @Query("select * from users")
    suspend fun getAllUsers(): List<UserEntity>?
}