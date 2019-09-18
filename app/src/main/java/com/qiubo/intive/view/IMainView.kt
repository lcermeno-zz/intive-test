package com.qiubo.intive.view

import com.qiubo.intive.entities.User

interface IMainView : IOnError {
    fun onGetItems(items: List<User>)
    fun onLoadMore(items: MutableList<User>)
}