package com.example.showcat.ui.secondscreen

import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.showcat.R
import com.example.showcat.databinding.FragmentDetailsBinding
import com.example.showcat.ui.model.CatUI
import java.io.File
import java.io.FileOutputStream
import java.util.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)

        val photo = args.photo
        displayDetailedPhotos(binding, photo)
        saveDetailedPhotos(binding, photo)
    }

    private fun saveDetailedPhotos(binding: FragmentDetailsBinding, photo: CatUI) {
        binding.apply {


            savaImage.setOnClickListener {
                val bitmapDrawable: BitmapDrawable = imageView.drawable as BitmapDrawable
                val bitmap: Bitmap = bitmapDrawable.bitmap
                if (checkPermission()) {
                    saveImageToGallery(bitmap, photo)
                } else {
                    requestPermissions(
                        arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ), REQUEST_CODE
                    )
                    saveImageToGallery(bitmap, photo)
                }
            }
        }
    }

    private fun displayDetailedPhotos(binding: FragmentDetailsBinding, photo: CatUI) {

        binding.apply {
            Glide.with(this@DetailsFragment)
                .load(photo.imageUrl)
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

    private fun saveImageToGallery(bitmap: Bitmap, photo: CatUI) {
        try {
            val fos = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                val resolver: ContentResolver? = activity?.contentResolver
                val contentValues = ContentValues()
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, photo.id + ".jpg")
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                contentValues.put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + File.separator + "TestFolder"
                )
                val imageUri: Uri =
                    resolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!
                resolver.openOutputStream(imageUri)
            } else {
                val imagesDir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .toString()
                val image = File(imagesDir, photo.id + ".jpg")
                FileOutputStream(image)
            }

            bitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY, fos)
            fos?.close()

            Toast.makeText(context, "Картинка сохранена", Toast.LENGTH_SHORT).show()

        } catch (e : Exception) {
            Toast.makeText(context, "Картинка не сохранена \n" + e.message, Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun checkPermission(): Boolean {
        val write = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val read = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        return write == PackageManager.PERMISSION_GRANTED && read == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Разрешение получено", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val REQUEST_CODE = 111
        const val QUALITY = 100
    }
}
