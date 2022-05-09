package com.tuly.userdatabase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 5, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {
    abstract val userDao:UserDao
}