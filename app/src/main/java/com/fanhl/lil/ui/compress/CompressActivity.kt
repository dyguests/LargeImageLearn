package com.fanhl.lil.ui.compress

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.fanhl.lil.R
import kotlinx.android.synthetic.main.activity_compress.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

/**
 * Reducing Image Size
 */
class CompressActivity : AppCompatActivity() {
    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compress)

        // 两种压缩方法

        // 1. 尺寸压缩
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_large, BitmapFactory.Options().apply {
            inSampleSize = 4
        })

        img_1.setImageBitmap(bitmap)


        object : AsyncTask<Void, Void, Bitmap?>() {
            override fun doInBackground(vararg p0: Void?): Bitmap? {
                val bitmapSrc = BitmapFactory.decodeResource(resources, R.drawable.img_600k, BitmapFactory.Options())

                return compressBitmap(bitmapSrc, 512)
            }

            override fun onPostExecute(result: Bitmap?) {
                img_2.setImageBitmap(result)
            }
        }.execute()
    }

    /**
     * 压缩图片
     *
     * @param bitmap
     * 被压缩的图片
     * @param sizeLimit
     * 大小限制
     * @return
     * 压缩后的图片
     */
    private fun compressBitmap(bitmap: Bitmap, sizeLimit: Long): Bitmap? {
        val baos = ByteArrayOutputStream()
        var quality = 100
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)

        // 循环判断压缩后图片是否超过限制大小
        while (baos.toByteArray().size / 1024 > sizeLimit) {
            Log.i("CompressActivity", "quality:$quality")
            // 清空baos
            baos.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
            quality /= 2
            if (quality < 1) {
                break
            }
        }

        return BitmapFactory.decodeStream(ByteArrayInputStream(baos.toByteArray()), null, null)
    }
}
