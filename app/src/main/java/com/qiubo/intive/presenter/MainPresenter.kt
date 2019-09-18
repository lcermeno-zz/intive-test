package com.qiubo.intive.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.qiubo.intive.entities.User
import com.qiubo.intive.model.domain.GetUserUseCase
import com.qiubo.intive.view.IMainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    private var mView: IMainView?,
    private val mGetUserUseCase: GetUserUseCase,
    private val width: Int
) :
    IMainPresenter {

    override fun onCreate() {
        getUsers()
    }

    override fun onDestroy() {
        mView = null
    }

    private var mPage = 1
    private var mLoading = false

    companion object {
        private const val TAG = "MainPresenter"
    }

    override fun getUsers() {
        if (!mLoading) {
            mPage = 1
            val list = mutableListOf<User>()
            requestData(list) { mView?.onGetItems(list) }
        }
    }

    override fun loadMoreItems(isBottom: Boolean) {
        if (!mLoading) {
            mPage++
            val list = mutableListOf<User>()
            requestData(list) { mView?.onLoadMore(list) }
        }
    }

    @SuppressLint("CheckResult")
    private fun requestData(list: MutableList<User>, function: (List<User>) -> Unit) {
        mLoading = true
        mGetUserUseCase.getAll(mPage)
            .map {

                var row = 0

                for ((index, User) in it.results.withIndex()) {

                    if (index % 3 == 0) row++

                    when (row % 3) {
                        0 -> assignItemSize(1, index, User)
                        1 -> assignItemSize(2, index, User)
                        2 -> assignItemSize(3, index, User)
                    }
                }
                it
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "Received album list with size: ${it.results.size}")
                list.addAll(it.results)
                function(list)
                mLoading = false
            }, {
                Log.e(TAG, it.message)
                it.printStackTrace()
                it.message?.let { message -> mView?.onError(message) }
            })
    }

    private fun assignItemSize(option: Int, index: Int, User: User) {
        when (option) {
            1 -> when {
                index % 3 == 0 -> User.size = width * 0.5f
                index % 3 == 1 -> User.size = width * 0.3f
                else -> User.size = width * 0.2f
            }
            2 -> when {
                index % 3 == 0 -> User.size = width * 0.2f
                index % 3 == 1 -> User.size = width * 0.5f
                else -> User.size = width * 0.3f
            }
            3 -> when {
                index % 3 == 0 -> User.size = width * 0.3f
                index % 3 == 1 -> User.size = width * 0.2f
                else -> User.size = width * 0.5f
            }
        }
    }
}