package com.qiubo.intive.model.repository

import com.qiubo.intive.api.HttpClientFactory
import com.qiubo.intive.api.service.IRandomeUserService
import com.qiubo.intive.entities.RandomUserResponse
import io.reactivex.Observable

class RandomUserRepository : IRandomUserRepository {
    override fun getUsers(page: Int): Observable<RandomUserResponse> =
        HttpClientFactory.getService(IRandomeUserService::class.java).getUsers(page = page)
}