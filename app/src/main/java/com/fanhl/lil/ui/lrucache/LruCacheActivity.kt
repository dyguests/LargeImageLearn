package com.fanhl.lil.ui.lrucache

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fanhl.lil.R

/**
 * 使用LruCache来实现图片缓存
 */
class LruCacheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lru_cache)
    }
}
