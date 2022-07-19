package com.aadi.pixabaysample.screens.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aadi.pixabaysample.R
import com.aadi.pixabaysample.adapter.ImageResultAdapter
import com.aadi.pixabaysample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment() : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mContext = view.context
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        lifecycleScope.launchWhenStarted {
            viewModel.results.collectLatest {

                val res = viewModel.results.value

                if(!res.isLoading) {
                    binding.loadingLayout.progressBar.visibility = View.GONE

                    if(res.error.isNotBlank()){
                        binding.errorLayout.rootLayout.visibility = View.VISIBLE
                        binding.errorLayout.errorSubtitle.text = res.error.toString()

                    } else {
                        binding.recyclerView.visibility = View.VISIBLE
                        //Toast.makeText(mContext, "Data available", Toast.LENGTH_SHORT).show()

                        Log.d("TAG", res.data.toString())
                        binding.recyclerView.adapter = ImageResultAdapter(res.data!!)
                        binding.recyclerView.layoutManager = LinearLayoutManager(mContext)
                    }
                }
            }
        }
    }

}