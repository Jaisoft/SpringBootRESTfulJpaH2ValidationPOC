package com.jaisoft.jpa.dto

data class CreateUserDto(val firstName: String, val lastName: String )

data class CreatedUserDto(val userId:Long, val firstName: String, val lastName: String)

data class UpdateUserDto( val firstName: String, val lastName: String)

data class UpdatedUserDto(val userId:Long, val firstName: String, val lastName: String)


