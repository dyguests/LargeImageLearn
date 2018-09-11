package com.fanhl.lil.ui.lrucache

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.util.LruCache
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.fanhl.lil.R
import kotlinx.android.synthetic.main.activity_brd.*


/**
 * 使用LruCache来实现图片缓存
 */
class LruCacheActivity : AppCompatActivity() {

    private var memoryCache: LruCache<String, Bitmap>? = null

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brd)

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


        fab.setOnClickListener {
            //            val resId = listOf(R.drawable.img_big, R.drawable.img_600k)[count++ % 2]
            val resId = listOf(R.drawable.img_100k, R.drawable.img_100k2)[count++ % 2]

            val imgKey = resId.toString()
            val bitmap = getBitmapFromMemCache(imgKey)
            if (bitmap != null) {
                img_large.setImageBitmap(bitmap)
            } else {
                BitmapWorkerTask(img_large).execute(resId)
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

    @SuppressLint("StaticFieldLeak")
    internal inner class BitmapWorkerTask(val imageView: ImageView) : AsyncTask<Int, Void, Bitmap>() {
        // 在后台加载图片。
        override fun doInBackground(vararg params: Int?): Bitmap {
            val bitmap = BitmapFactory.decodeResource(resources, params[0]!!)
            addBitmapToMemoryCache(params[0].toString(), bitmap)
            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            imageView.setImageBitmap(result)
        }
    }

}