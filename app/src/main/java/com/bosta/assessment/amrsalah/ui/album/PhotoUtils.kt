package com.bosta.assessment.amrsalah.ui.album

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

object PhotoUtils {
    fun getUriFromDrawable(context: Context, drawable: Drawable): Uri? {
        val bitmap = (drawable as BitmapDrawable).bitmap
        try {
            val file = File(
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "share_image_" + System.currentTimeMillis() + ".png"
            )

            FileOutputStream(file).let {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                it.close()
            }

            val photoUri = FileProvider.getUriForFile(
                context,
                context.applicationContext.packageName + ".provider",
                file
            )

            return photoUri
        } catch (e: Exception) {
            return null
        }
    }

    fun sharePhoto(context: Context, photoUri: Uri) {
        Intent(Intent.ACTION_SEND)
            .apply {
                type = "image/*"
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                putExtra(Intent.EXTRA_STREAM, photoUri)
            }
            .let { Intent.createChooser(it, "Share Photo") }
            .let { context.startActivity(it) }
    }
}
