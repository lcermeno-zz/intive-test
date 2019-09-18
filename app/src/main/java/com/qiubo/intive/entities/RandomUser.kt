package com.qiubo.intive.entities

data class RandomUserResponse(val results: List<User>)
data class User(
    val id: Id,
    val name: Name,
    val picture: Picture,
    val gender: String,
    val email: String,
    val nat: String
)

data class Name(val title: String, val first: String, val last: String)
data class Id(val name: String, val value: String)
data class Picture(val large: String, val medium: String, val thumbnail: String)