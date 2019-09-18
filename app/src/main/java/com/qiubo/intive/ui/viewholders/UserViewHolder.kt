package com.qiubo.intive.ui.viewholders

import android.view.View
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import kotlinx.android.extensions.LayoutContainer
import kotlin.math.roundToInt
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.qiubo.intive.entities.User
import com.qiubo.intive.ui.adapters.UserAdapter
import kotlinx.android.synthetic.main.recycler_user_item.*


class UserViewHolder(
    override val containerView: View,
    private val mListener: UserAdapter.IOnClickListener
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

    fun setValues(item: User) {
        photoRoot.layoutParams.height = item.size.roundToInt()
        photoRoot.requestLayout()
        photoImg.requestLayout()
        photoImg.setOnClickListener {
            val pair: Pair<View, String> = Pair(photoImg, "transitionPhoto")
            mListener.onClickItem(item, pair)
        }
        Glide
            .with(containerView.context)
            .load(item.picture.large)
            .transition(withCrossFade(factory))
            .into(photoImg)
    }
}