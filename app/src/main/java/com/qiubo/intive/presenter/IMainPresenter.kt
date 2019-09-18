package com.qiubo.intive.presenter

interface IMainPresenter : IOnCreate, IOnDestroy {
    fun getUsers()
    fun loadMoreItems(isBottom: Boolean)
}