package com.qiubo.intive.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class RandomUserResponse(val results: List<User>)
@Parcelize
data class User(
    val name: Name,
    val picture: Picture,
    val gender: String,
    val email: String,
    val login: Login,
    var size: Float = 0f
) : Parcelable

@Parcelize
data class Name(val title: String, val first: String, val last: String) : Parcelable


@Parcelize
data class Picture(val large: String, val medium: String, val thumbnail: String) : Parcelable

@Parcelize
data class Login(val username: String) : Parcelable