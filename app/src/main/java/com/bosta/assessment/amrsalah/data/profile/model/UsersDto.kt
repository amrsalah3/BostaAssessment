package com.bosta.assessment.amrsalah.data.profile.model

class UsersDto : ArrayList<UserDto>()

data class UserDto(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressDto,
    val phone: String,
    val website: String,
    val company: CompanyDto
)

data class AddressDto(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoDto
)

data class CompanyDto(
    val name: String,
    val catchPhrase: String,
    val bs: String
)

data class GeoDto(
    val lat: String,
    val lng: String
)
