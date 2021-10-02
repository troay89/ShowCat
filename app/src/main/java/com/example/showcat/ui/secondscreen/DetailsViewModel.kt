package com.example.showcat.ui.secondscreen

import android.app.Application
import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.showcat.R
import com.example.showcat.databinding.FragmentDetailsBinding
import com.example.showcat.ui.model.CatUI
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val app: Application
): ViewModel() {

    val catUI = state.get<CatUI>("photo")

    fun displayDetailedPhotos(binding: FragmentDetailsBinding) {

        binding.apply {
            Glide.with(app)
                .load(catUI?.imageUrl)
                .error(R.drawable.ic_baseline_person_24)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                })
                .into(imageView)
        }
    }

    fun saveImageToGallery(bitmap: Bitmap) {
        try {
            val fos = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                val resolver: ContentResolver = app.contentResolver
                val contentValues = ContentValues()
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, catUI?.id + ".jpg")
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                contentValues.put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + File.separator + "TestFolder"
                )
                val imageUri: Uri =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!
                resolver.openOutputStream(imageUri)
            } else {
                val imagesDir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .toString()
                val image = File(imagesDir, catUI?.id + ".jpg")
                FileOutputStream(image)
            }

            bitmap.compress(Bitmap.CompressFormat.JPEG, DetailsFragment.QUALITY, fos)
            fos?.close()

            Toast.makeText(app, "картинка сохранена", Toast.LENGTH_LONG).show()
        } catch (e : Exception) {
            Toast.makeText(app, "картинку не удалось сохранить", Toast.LENGTH_LONG).show()
        }
    }

}
