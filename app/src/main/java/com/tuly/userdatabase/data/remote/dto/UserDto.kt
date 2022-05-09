package com.tuly.userdatabase.data.remote.dto

data class UserDto (
    val email: String,
    val gender: String,
    val location: LocationDto,
    val picture: PictureDto,
    val login: LoginDto,
    val dob: DateOfBirthDto,
    val name: NameDto,
    val nat: String
)