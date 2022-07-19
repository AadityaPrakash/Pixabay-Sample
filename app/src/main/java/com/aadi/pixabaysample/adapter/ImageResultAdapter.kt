package com.aadi.pixabaysample.adapter

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aadi.pixabay.domain.models.ImagesModel
import com.aadi.pixabaysample.databinding.ItemSearchResultBinding
import com.bumptech.glide.Glide

class ImageResultAdapter(val imageList: List<ImagesModel>) : RecyclerView.Adapter<ImageResultAdapter.ResultsViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val binding = ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        mContext = parent.context
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        Log.d("TAG", imageList.toString())
        holder.binding.itemTitle.text = imageList[position].user
        holder.binding.itemSubtitle.text = imageList[position].tags
        Glide.with(mContext).load(imageList[position].previewURL).into(holder.binding.itemImg)
    }

    override fun getItemCount() = imageList.size

    class ResultsViewHolder(val binding: ItemSearchResultBinding) : RecyclerView.ViewHolder(binding.root)
}