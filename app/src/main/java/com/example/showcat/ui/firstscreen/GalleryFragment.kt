package com.example.showcat.ui.firstscreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.map
import com.example.showcat.R
import com.example.showcat.databinding.FragmentGalleryBinding
import com.example.showcat.ui.mapper.EntityToUI
import com.example.showcat.ui.model.CatUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val viewModel by viewModels<GalleryViewModel>()

    private lateinit var adapter: CatPhotoAdapter
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)

        populateRecyclerView()
        setListeners()
        observerLiveData()
        loadStateListener()
    }

    private fun populateRecyclerView() {
        binding.apply {
            adapter = CatPhotoAdapter { photo ->
                onItemClick(photo)
            }
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
    }

    private fun setListeners() {
        binding.apply {
            buttonRetry.setOnClickListener {
                adapter.retry()
            }
        }
    }

    private fun observerLiveData() {
        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it.map { catEntity ->
                EntityToUI.map(catEntity)
            })
        }
    }

    private fun loadStateListener() {
        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error
            }
        }
    }

    private fun onItemClick(photo: CatUI) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(photo)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}