package com.fanhl.lil.ui.moving

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fanhl.lil.R
import kotlinx.android.synthetic.main.activity_moving.*

/**
 * 动图加载
 */
class MovingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moving)

        arrayOf(
                R.drawable.gif_large/*,
                R.drawable.heic_1,
                R.drawable.heic_animation_1*/
        )

        fab.setOnClickListener {
            Glide.with(img_1)
                    .load(R.drawable.gif_large)
                    .into(img_1)
        }
    }
}
