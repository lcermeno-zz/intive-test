package com.qiubo.intive.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.qiubo.intive.R
import com.qiubo.intive.entities.User
import com.qiubo.intive.misc.Constants
import com.qiubo.intive.misc.ScreenHelper
import com.qiubo.intive.model.domain.GetUserUseCase
import com.qiubo.intive.presenter.IMainPresenter
import com.qiubo.intive.presenter.MainPresenter
import com.qiubo.intive.ui.adapters.UserAdapter
import com.qiubo.intive.view.IMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView, UserAdapter.IOnClickListener {

    private lateinit var mPresenter: IMainPresenter
    private val mAdapter by lazy { UserAdapter(mutableListOf(), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecycler.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        mainRecycler.adapter = mAdapter

        mainRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                mainRefresh.isRefreshing = true
                mPresenter.loadMoreItems(!mainRecycler.canScrollVertically(1))
            }
        })

        mainRefresh.setOnRefreshListener { mPresenter.getUsers() }

        val width = ScreenHelper.getScreenRealWidth(this)

        mPresenter = MainPresenter(this, GetUserUseCase(), width)
        mPresenter.onCreate()
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }

    override fun onGetItems(items: List<User>) {
        mAdapter.setItems(items)
        mainRefresh.isRefreshing = false
    }

    override fun onLoadMore(items: MutableList<User>) {
        mainRefresh.isRefreshing = false
        mAdapter.loadMore(items)
    }

    override fun onClickItem(item: User, pair: Pair<View, String>) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Constants.ITEM_KEY, item)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            startActivity(intent, optionsCompat.toBundle())
        else
            startActivity(intent)
    }

    override fun onError(message: String) {
        mainRefresh.isRefreshing = false
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
