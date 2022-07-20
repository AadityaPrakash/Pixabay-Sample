package com.aadi.pixabaysample.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.aadi.pixabay.domain.models.ImagesModel
import com.aadi.pixabaysample.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var item: ImagesModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        item = args.image
        populateUI()
    }

    private fun populateUI() {

        binding.apply {
            detailTitle.text = item.user
            detailSubtitle.text = item.tags
            detailImage.load(item.largeImageURL) {
                crossfade(true)
            }
        }
    }

}