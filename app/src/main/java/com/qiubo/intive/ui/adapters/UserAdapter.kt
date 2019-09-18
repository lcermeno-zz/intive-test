package com.qiubo.intive.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.qiubo.intive.R
import com.qiubo.intive.entities.User
import com.qiubo.intive.ui.viewholders.UserViewHolder

class UserAdapter(
    private val mItems: MutableList<User>,
    private val mListener: IOnClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface IOnClickListener {
        fun onClickItem(
            item: User,
            pair: Pair<View, String>
        )
    }

    override fun getItemCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_user_item, parent, false)
        return UserViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as UserViewHolder
        viewHolder.setValues(mItems[position])
    }

    fun setItems(items: List<User>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    fun loadMore(items: MutableList<User>) {
        val start = mItems.size - 1
        mItems.addAll(items)
        notifyItemRangeChanged(start, mItems.size)
    }
}