package com.qiubo.intive.presenter

interface IMainPresenter {
    fun getUsers()
    fun loadMoreItems(isBottom: Boolean)
}