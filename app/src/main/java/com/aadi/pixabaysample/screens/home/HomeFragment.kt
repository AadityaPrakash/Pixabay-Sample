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
import com.aadi.pixabaysample.R
import com.aadi.pixabaysample.toolkit.Utils
import com.aadi.pixabaysample.adapter.ImageResultAdapter
import com.aadi.pixabaysample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment() : Fragment() {

    private lateinit var imageResultAdapter: ImageResultAdapter
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

                    if(!res.data.isNullOrEmpty()){
                        imageResultAdapter.setImageAdapter(res.data!!)
                        imageResultAdapter.notifyDataSetChanged()
                    }
                    else
                        showErrorLayout()
                }
            }
        }
    }

    private fun initLayout() {

        imageResultAdapter = ImageResultAdapter()
        binding.recyclerView.adapter = imageResultAdapter
        binding.recyclerView.setHasFixedSize(true)

        binding.sb.apply {
            etSearch.setOnFocusChangeListener { _, hasFocus ->
                toolbarLeftBtn.visibility = if(hasFocus) View.VISIBLE else View.GONE
            }
            etSearch.setOnEditorActionListener(object: TextView.OnEditorActionListener {

                override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        Utils.hideKeyboard(v.context, etSearch)
                        viewModel.searchQuery(v.context, etSearch.text.toString())

                        return true
                    }
                    return false
                }
            })
            toolbarLeftBtn.setOnClickListener {
                if(etSearch.text.isNotBlank()) etSearch.text.clear() else Utils.hideKeyboard(it.context, etSearch)
            }
        }
    }

    private fun showErrorLayout(error: String) {
        binding.apply {
            errorLayout.rootLayout.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            errorLayout.errorSubtitle.text = error
        }
    }

    private fun showErrorLayout() {
        binding.apply {
            errorLayout.rootLayout.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            errorLayout.errorTitle.text = resources.getText(R.string.msg_empty_title)
            errorLayout.errorSubtitle.text = resources.getText(R.string.msg_empty)
        }
    }

    private fun hideLoading() {
        binding.apply {
            loadingLayout.progressBar.visibility = View.GONE
            errorLayout.rootLayout.visibility = View. GONE
        }
    }
}
