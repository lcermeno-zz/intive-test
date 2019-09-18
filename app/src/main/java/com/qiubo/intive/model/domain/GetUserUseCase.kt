package com.qiubo.intive.model.domain

import com.qiubo.intive.model.repository.RandomUserRepository

class GetUserUseCase {

    fun getAll(page: Int) = RandomUserRepository().getUsers(page)
}