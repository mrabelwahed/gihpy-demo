package com.tarek360.giphy.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.tarek360.giphy.R
import com.tarek360.giphy.onLoadFailed
import com.tarek360.giphy.onResourceReady
import kotlinx.android.synthetic.main.gif_details.*

class GifDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gif_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadGif()
    }

    private fun loadGif() {
        context?.let { context ->
            val drawable = getLoaderDrawable(context)
            arguments?.let {
                if (it.containsKey(ARG_GIF_URL)) {
                    val url = it.getString(ARG_GIF_URL)
                    Glide.with(this)
                        .asGif()
                        .load(url)
                        .onResourceReady { drawable.stop() }
                        .onLoadFailed { drawable.stop() }
                        .placeholder(drawable)
                        .centerCrop()
                        .into(gifImageView)

                }
            }
        }

    }

    private fun getLoaderDrawable(context: Context): CircularProgressDrawable {
        return CircularProgressDrawable(context).apply {
            setColorSchemeColors(ContextCompat.getColor(context, R.color.colorAccent))
            strokeWidth = 8f
            centerRadius = 100f
            start()
        }
    }

    override fun onDestroyView() {
        Glide.with(this).clear(gifImageView)
        super.onDestroyView()
    }

    companion object {
        private const val ARG_GIF_URL = "gif_url"

        fun buildBundle(gifUrl: String): Bundle = Bundle().apply {
            putString(ARG_GIF_URL, gifUrl)
        }
    }
}
