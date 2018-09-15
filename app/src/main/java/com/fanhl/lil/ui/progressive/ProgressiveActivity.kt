package com.fanhl.lil.ui.progressive

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fanhl.lil.R
import kotlinx.android.synthetic.main.activity_brd.*
import org.jetbrains.anko.toast

/**
 * 不推荐，反而变慢了好像
 */
class ProgressiveActivity : AppCompatActivity() {

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brd)

        val action1 = {
            val currentTimeMillis = System.currentTimeMillis()
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_large, BitmapFactory.Options().apply {
                inSampleSize = 4
            })
            img_large.setImageBitmap(bitmap)
            toast("Large Cost:${System.currentTimeMillis() - currentTimeMillis}")
        }
        val action2 = {
            val currentTimeMillis = System.currentTimeMillis()
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_large_progressive, BitmapFactory.Options().apply {
                inSampleSize = 4
            })
            img_large.setImageBitmap(bitmap)
            toast("Large Progressive Cost:${System.currentTimeMillis() - currentTimeMillis}")
        }
        val actions = arrayOf(action1, action2)

        fab.setOnClickListener {
            actions[counter++ % 2].invoke()
        }
    }
}
