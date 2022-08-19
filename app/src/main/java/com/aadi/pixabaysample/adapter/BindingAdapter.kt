package com.aadi.pixabaysample.adapter

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aadi.pixabay.domain.models.ImagesModel
import com.aadi.pixabaysample.R

/**
 * Updates the data shown in the [RecyclerView].
 */
//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<ImagesModel>?) {
//    recyclerView.visibility = View.VISIBLE
//    val adapter = recyclerView.adapter as ImageResultAdapter?
//    adapter?.let {
//        adapter.submitList(data)
//    }
//}

/**
 * Uses the Coil library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            crossfade(true)
            placeholder(R.color.placeholderColor)
            error(R.drawable.ic_broken_image)
        }
    }
}
