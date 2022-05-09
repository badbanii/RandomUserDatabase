package com.tuly.userdatabase.data.mapper

import com.tuly.userdatabase.data.local.UserEntity
import com.tuly.userdatabase.data.remote.dto.UserDto
import com.tuly.userdatabase.domain.model.UserInfo
import javax.inject.Inject

class UserMapper @Inject constructor() {
    fun mapUserDtoToUserEntity(userDto: UserDto) = UserEntity(
            userDto.email,
            userDto.gender,
            manageStreetDto(userDto.location.street.number, userDto.location.street.name),
            userDto.location.city,
            userDto.location.state,
            userDto.location.postcode,
            userDto.picture.large,
            userDto.login.username,
            (userDto.dob.date).take(10),
            userDto.name.title,
            userDto.nat
        )

    fun mapUserEntityUserInfo(userEntity: UserEntity) = UserInfo(
        userEntity.email,
        userEntity.gender,
        manageAddress(userEntity.street, userEntity.city, userEntity.state, userEntity.postCode),
        userEntity.pictureLarge,
        userEntity.username,
        (userEntity.date).take(10),
        userEntity.title,
        userEntity.nat
    )

    fun mapGermanUserDtoToUserInfo(userDto: UserDto) = UserInfo(
        userDto.email,
        userDto.gender,
        manageAddress(manageStreetDto(userDto.location.street.number,userDto.location.street.name),userDto.location.city,userDto.location.state,userDto.location.postcode),
        userDto.picture.large,
        userDto.login.username,
        userDto.dob.date,
        userDto.name.title,
        userDto.nat
    )

    private fun manageStreetDto(number:Int,name:String)="$name $number"
    private fun manageAddress(street: String, city: String, state: String, postcode: String) = "$street $city $state $postcode"
}