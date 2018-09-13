package com.fanhl.lil.ui.compress

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fanhl.lil.R
import kotlinx.android.synthetic.main.activity_compress.*

/**
 * Reducing Image Size
 */
class CompressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compress)

        // 两种压缩方法

        // 1. 尺寸压缩
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_large, BitmapFactory.Options().apply {
            inSampleSize = 4
        })

        img_1.setImageBitmap(bitmap)

        // 2. 像素压缩、质量压缩
//        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.img_large, BitmapFactory.Options().apply {
//            //            inSampleSize = 4
//        })
//        val bos = ByteArrayOutputStream()
//
//        bitmap2.compress(Bitmap.CompressFormat.JPEG, 25, bos)
//
//        toast("Size:${bos.size() / 1024}KB")
    }
}
