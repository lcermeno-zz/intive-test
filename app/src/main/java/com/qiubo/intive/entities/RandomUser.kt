package com.qiubo.intive.entities

data class RandomUserResponse(val results: List<User>)
data class User(
    val gender: String,
    val email: String,
    val nat: String
)