package com.aadi.pixabaysample.screens.home

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.aadi.pixabaysample.toolkit.Utils
import com.aadi.pixabaysample.adapter.ImageResultAdapter
import com.aadi.pixabaysample.adapter.bindRecyclerView
import com.aadi.pixabaysample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment() : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()

        lifecycleScope.launchWhenResumed {
            viewModel.results.collectLatest {
                val res = viewModel.results.value

                if (!res.isLoading) {
                    hideLoading()

                    if (res.error.isNotBlank()) showErrorLayout(res.error)

                    res.data?.let { bindRecyclerView(binding.recyclerView, it) }
                }
            }
        }
    }

    private fun initLayout() {

        binding.etSearch.setOnEditorActionListener(object: TextView.OnEditorActionListener {

            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Utils.hideKeyboard(v.context, binding.etSearch)
                    viewModel.searchQuery(v.context, binding.etSearch.text.toString())

                    return true
                }
                return false
            }
        })

        binding.apply {
            recyclerView.adapter = ImageResultAdapter()
            lifecycleOwner = this@HomeFragment
            executePendingBindings()
        }
    }

    private fun showErrorLayout(error: String) {
        binding.apply {
            errorLayout.rootLayout.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            errorLayout.errorSubtitle.text = error
        }
    }

    private fun hideLoading() {
        binding.apply {
            loadingLayout.progressBar.visibility = View.GONE
        }
    }
}
