package com.example.showcat.ui.firstscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.showcat.R
import com.example.showcat.databinding.ItemUnsplashPhotoBinding
import com.example.showcat.ui.model.CatUI

class CatPhotoAdapter(private val onClick: (CatUI) -> Unit): PagingDataAdapter<CatUI, CatPhotoAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null){
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding, onClick)
    }

    inner class PhotoViewHolder(private val binding: ItemUnsplashPhotoBinding, onClick: (CatUI) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val  position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if(item != null){
                        onClick(item)
                    }
                }
            }
        }

        fun bind(catUI: CatUI){
            binding.apply {
                Glide.with(itemView)
                    .load(catUI.url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_person_24)
                    .into(imageView)
            }
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<CatUI>() {
            override fun areItemsTheSame(oldItem: CatUI, newItem: CatUI) =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: CatUI, newItem: CatUI) =
                oldItem == newItem
        }
    }
}