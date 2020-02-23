package com.tarek360.giphy.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.tarek360.domain.model.Image
import com.tarek360.giphy.R
import kotlinx.android.synthetic.main.item_image.view.*

class ImagesRecyclerViewAdapter :
    PagedListAdapter<Image, ImagesRecyclerViewAdapter.ImageViewHolder>(diffCallback) {

    var onItemClickListener: ((item: Image) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { item ->
            Glide.with(holder.itemView)
                .asGif()
                .load(item.lowFramesUrl)
                .placeholder(getLoaderDrawable(holder.itemView.context))
                .centerCrop()
                .into(holder.imageView)
            holder.itemView.setOnClickListener { onItemClickListener?.invoke(item) }
        }
    }

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.gifImageView
    }

    private fun getLoaderDrawable(context: Context): CircularProgressDrawable {
        return CircularProgressDrawable(context).apply {
            setColorSchemeColors(ContextCompat.getColor(context, R.color.colorAccent))
            strokeWidth = 5f
            centerRadius = 32f
            start()
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
                oldItem.lowFramesUrl == newItem.lowFramesUrl

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
                oldItem == newItem
        }
    }
}
