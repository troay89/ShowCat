package com.example.showcat.ui.firstscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.showcat.R
import com.example.showcat.data.api.model.CatApi
import com.example.showcat.databinding.ItemUnsplashPhotoBinding

class CatPhotoAdapter(private val onClick: (CatApi) -> Unit): PagingDataAdapter<CatApi, CatPhotoAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {


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

    inner class PhotoViewHolder(private val binding: ItemUnsplashPhotoBinding, onClick: (CatApi) -> Unit) :
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

        fun bind(catApi: CatApi){
            binding.apply {
                Glide.with(itemView)
                    .load(catApi.url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_person_24)
                    .into(imageView)
            }
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<CatApi>() {
            override fun areItemsTheSame(oldItem: CatApi, newItem: CatApi) =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: CatApi, newItem: CatApi) =
                oldItem == newItem
        }
    }
}