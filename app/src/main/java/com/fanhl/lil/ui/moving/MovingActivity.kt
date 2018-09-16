package com.fanhl.lil.ui.moving

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fanhl.lil.R
import kotlinx.android.synthetic.main.activity_moving.*

/**
 * 动图加载
 */
class MovingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moving)

        img_1.setImageResource(R.drawable.gif_large)
    }
}
