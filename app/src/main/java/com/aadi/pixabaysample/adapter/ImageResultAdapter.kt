package com.aadi.pixabaysample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aadi.pixabay.domain.models.ImagesModel
import com.aadi.pixabaysample.R
import com.aadi.pixabaysample.databinding.ItemSearchResultBinding
import com.aadi.pixabaysample.screens.home.HomeFragmentDirections
import com.aadi.pixabaysample.screens.home.HomeState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

class ImageResultAdapter
    : ListAdapter<ImagesModel, ImageResultAdapter.ResultsViewHolder>(DiffCallback) {

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        return ResultsViewHolder(ItemSearchResultBinding.inflate
            (LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {

        val currImage = getItem(position)
        holder.bind(currImage)

        holder.binding.itemCard.setOnClickListener { mView ->

            val mContext = mView.context
            MaterialAlertDialogBuilder(mContext)
                .setTitle(mContext.resources.getString(R.string.alertTitle))
                .setMessage(mContext.resources.getString(R.string.alertSubtitle))
                .setNegativeButton(mContext.resources.getString(android.R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }

                .setPositiveButton(mContext.resources.getString(android.R.string.ok)) { dialog, _ ->
                    dialog.dismiss()
                    val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(currImage)
                    mView.findNavController().navigate(direction)
                }
                .show()

        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [ImagesModel] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<ImagesModel>() {
        override fun areItemsTheSame(oldItem: ImagesModel, newItem: ImagesModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImagesModel, newItem: ImagesModel): Boolean {
            return oldItem.previewURL == newItem.previewURL
        }
    }

    inner class ResultsViewHolder(val binding: ItemSearchResultBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: ImagesModel) {
            binding.item = image
        }
    }
}