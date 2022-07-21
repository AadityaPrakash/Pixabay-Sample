package com.aadi.pixabaysample.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aadi.pixabay.domain.models.ImagesModel
import com.aadi.pixabaysample.databinding.FragmentDetailBinding
import com.aadi.pixabaysample.toolkit.Utils
import com.google.android.material.chip.Chip


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }
        populateUI()
    }

    private fun populateUI() {
        bind(args.image)
    }

    private fun bind(item: ImagesModel) {
        binding.detail = item
        genTags(Utils.getTagList(item.tags))
    }

    private fun genTags(tagList: Array<String>) {
        for (tag in tagList) {
            val chip = Chip(context)
            chip.text = tag
            binding.chipsPrograms.addView(chip)
        }
    }

}