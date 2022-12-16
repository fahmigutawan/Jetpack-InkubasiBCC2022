package com.bcc.brain.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.sql.Blob

object ImageConverters {
    fun encodeUriToString(context: Context, uri:Uri):String{
        val input = context.getContentResolver().openInputStream(uri)
        val image = BitmapFactory.decodeStream(input , null, null)

        // Encode image to base64 string
        val baos = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val imageBytes = baos.toByteArray()
        val imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)
        // No Need 'data:image/jpeg;base64,' at the beginning. So just return imageString directly

        input?.close()

//        return imageString
        return "data:image/jpeg;base64,$imageString"
    }

    fun encodeBitmapToString(image: Bitmap?): String {
        val baos = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val imageBytes = baos.toByteArray()

        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    fun decodeStringToBitmap(imageString: String): Bitmap {
        // Decode base64 string to image
        val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}