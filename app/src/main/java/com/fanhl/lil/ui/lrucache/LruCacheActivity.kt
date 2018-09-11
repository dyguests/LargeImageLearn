package com.fanhl.lil.ui.lrucache

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.util.LruCache
import android.support.v7.app.AppCompatActivity
import com.fanhl.lil.R


/**
 * 使用LruCache来实现图片缓存
 */
class LruCacheActivity : AppCompatActivity() {

    private var memoryCache: LruCache<String, Bitmap>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lru_cache)

        // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。
        // LruCache通过构造函数传入缓存值，以KB为单位。
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        // 使用最大可用内存值的1/8作为缓存的大小。
        val cacheSize = maxMemory / 8

        memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                return bitmap.byteCount / 1024
            }
        }
    }

    fun addBitmapToMemoryCache(key: String, bitmap: Bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            memoryCache?.put(key, bitmap)
        }
    }

    fun getBitmapFromMemCache(key: String): Bitmap? {
        return memoryCache?.get(key)
    }
}