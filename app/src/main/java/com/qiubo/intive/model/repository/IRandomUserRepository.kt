package com.qiubo.intive.model.repository

import com.qiubo.intive.entities.RandomUserResponse
import io.reactivex.Observable

interface IRandomUserRepository {
    fun getUsers(page: Int): Observable<RandomUserResponse>
}