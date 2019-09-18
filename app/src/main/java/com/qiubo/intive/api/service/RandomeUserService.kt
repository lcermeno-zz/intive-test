package com.qiubo.intive.api.service

import com.qiubo.intive.entities.RandomUserResponse
import com.qiubo.intive.misc.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomeUserService {
    @GET("")
    fun getUsers(@Query(Constants.LIMIT_KEY) limit: Int = Constants.PAGE_LIMIT,
                  @Query(Constants.PAGE_KEY) page: Int): Observable<RandomUserResponse>
}