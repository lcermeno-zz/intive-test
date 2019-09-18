package com.qiubo.intive.ui

import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.qiubo.intive.R
import com.qiubo.intive.entities.User
import com.qiubo.intive.misc.Constants
import com.qiubo.intive.misc.TextHelper
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val UI_ANIMATION_DELAY = 800L
    }

    private val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val user = intent.getParcelableExtra<User>(Constants.ITEM_KEY)


        val nameLabel = "${user.name.title} ${user.name.first} ${user.name.last}"
        detail_name.text = TextHelper.capitalize(nameLabel)
        detail_username.text = "@${user.login.username}"
        detail_email.text = "@${user.email}"

        loadPicture(
            user.picture.large,
            detail_bg,
            RequestOptions.bitmapTransform(BlurTransformation(25, 3))
        )
        Handler().postDelayed(
            { loadPicture(user.picture.large, detail_picture, RequestOptions.circleCropTransform()) },
            UI_ANIMATION_DELAY
        )
    }

    private fun loadPicture(
        picture: String,
        imageView: ImageView,
        bitmapTransform: RequestOptions
    ) {
        Glide
            .with(this)
            .load(picture)
            .apply(bitmapTransform)
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .into(imageView)
    }
}
