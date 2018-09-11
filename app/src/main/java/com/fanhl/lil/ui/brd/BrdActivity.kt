package com.fanhl.lil.ui.brd

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapRegionDecoder
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fanhl.lil.R
import kotlinx.android.synthetic.main.activity_brd.*
import java.io.IOException

/**
 * BitmapRegionDecoder
 */
class BrdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brd)

        try {
            val ins = assets.open("img_large.jpg")

            //获得图片的宽、高
            val tmpOptions = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            BitmapFactory.decodeStream(ins, null, tmpOptions)
            val width = tmpOptions.outWidth
            val height = tmpOptions.outHeight

            //设置显示图片的中心区域
            val bitmapRegionDecoder = BitmapRegionDecoder.newInstance(ins, false)
            val options = BitmapFactory.Options().apply {
                inPreferredConfig = Bitmap.Config.RGB_565
            }

            val bitmap = bitmapRegionDecoder.decodeRegion(Rect(width / 2 - 100, height / 2 - 100, width / 2 + 100, height / 2 + 100), options)

            img_large.setImageBitmap(bitmap)
        } catch (e: IOException) {
        } finally {
        }
    }
}
