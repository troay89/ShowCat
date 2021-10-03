package com.example.showcat.ui.secondscreen

import android.Manifest
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.showcat.R
import com.example.showcat.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        viewModel.displayDetailedPhotos(binding)
        saveDetailedPhotos()
    }

    private fun saveDetailedPhotos() {
        binding.savaImage.setOnClickListener {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                viewModel.saveImageToGallery(binding)
                return@setOnClickListener
            }
            requestWritePermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

    }

    private val requestWritePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        ::onGotWritePermissionsResult
    )


    private fun onGotWritePermissionsResult(granted: Boolean) {
        if (granted) {
            viewModel.saveImageToGallery(binding)
            Toast.makeText(context, "разрешение получено", Toast.LENGTH_LONG).show()
        } else Toast.makeText(context, "разрешение не получено", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val QUALITY = 100
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

