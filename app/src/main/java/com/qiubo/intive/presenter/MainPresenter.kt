package com.qiubo.intive.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.qiubo.intive.entities.User
import com.qiubo.intive.model.domain.GetUserUseCase
import com.qiubo.intive.view.IMainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private var mView: IMainView, private val mGetUserUseCase: GetUserUseCase) :
    IMainPresenter {
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
        mGetUserUseCase
            .getAll(mPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "Received User list with size: ${it.results.size}")
                list.addAll(it.results)
                function(list)
            }, {
                Log.e(TAG, it.message)
                it.printStackTrace()
                it.message?.let { message -> mView?.onError(message) }
            })
    }
}