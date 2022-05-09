package com.tuly.userdatabase.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class UserEntity constructor(
    @PrimaryKey val email: String,
    val gender: String,
    val street: String,
    val city: String,
    val state: String,
    val postCode: String,
    val pictureLarge: String,
    val username: String,
    val date: String,
    val title: String,
    val nat: String
)
