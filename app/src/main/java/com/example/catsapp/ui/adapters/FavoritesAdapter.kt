package com.example.catsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catsapp.R
import com.example.catsapp.databinding.ItemCatBinding
import com.example.catsapp.databinding.ItemFavoriteBinding
import com.example.catsapp.model.dbclasses.CatWithBreedAndCategory
import javax.inject.Inject

class FavoritesAdapter @Inject constructor() : ListAdapter<CatWithBreedAndCategory, FavoritesAdapter.ResponseHolder>(
    DIFF_CALLBACK
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResponseHolder(binding)
    }

    override fun onBindViewHolder(holder: ResponseHolder, position: Int) {
        val cat = getItem(position)
        holder.bind(cat)
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CatWithBreedAndCategory>() {

            override fun areItemsTheSame(oldItem: CatWithBreedAndCategory, newItem: CatWithBreedAndCategory) =
                oldItem.cat.id == newItem.cat.id

            override fun areContentsTheSame(oldItem: CatWithBreedAndCategory, newItem: CatWithBreedAndCategory) = oldItem == newItem
        }
    }

    inner class ResponseHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: CatWithBreedAndCategory?) {
            Glide.with(binding.root)
                .load(cat?.cat?.url)
                .placeholder(R.drawable.cat_placeholder)
                .centerCrop()
                .dontAnimate()
                .into(binding.itemImage)
        }

    }
}