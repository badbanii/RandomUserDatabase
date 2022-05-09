package com.tuly.userdatabase.data.remote.dto

data class LocationDto(
    val street: StreetDto,
    val city: String,
    val state: String,
    val postcode: String
    )