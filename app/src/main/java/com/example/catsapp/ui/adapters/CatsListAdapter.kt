package com.example.catsapp.ui.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catsapp.R
import com.example.catsapp.databinding.ItemCatBinding
import com.example.catsapp.model.Cat

class CatsListAdapter(private val onCardClickListener: CardClickListener) : PagedListAdapter<Cat, CatsListAdapter.ResponseHolder>(
    DIFF_CALLBACK
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseHolder {
        val binding = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResponseHolder(binding)
    }

    override fun onBindViewHolder(holder: ResponseHolder, position: Int) {
        val cat = getItem(position)
        holder.bind(cat, position)
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Cat>() {

            override fun areItemsTheSame(oldItem: Cat, newItem: Cat) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat) = oldItem == newItem
        }
    }

    inner class ResponseHolder(private val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: Cat?, position: Int) {
            cat?.let { binding.itemFavoriteButton.isSelected = it.isExistInRoom }
            binding.itemFavoriteButton.setOnClickListener { view ->
                cat?.let {
                    onCardClickListener.onFavoriteButtonClick(cat)
                    view.isSelected = !view.isSelected
                }
            }
            binding.itemDownloadButton.setOnClickListener {
                cat?.let {
                    onCardClickListener.onDownloadButtonClick(it)
                }
            }
            binding.itemPosition.text = position.toString()
            Glide.with(binding.root)
                .load(cat?.url)
                .placeholder(R.drawable.cat_placeholder)
                .centerCrop()
                .dontAnimate()
                .into(binding.itemImage)
        }

    }

    interface CardClickListener{
        fun onFavoriteButtonClick(cat: Cat)
        fun onDownloadButtonClick(cat: Cat)
    }
}