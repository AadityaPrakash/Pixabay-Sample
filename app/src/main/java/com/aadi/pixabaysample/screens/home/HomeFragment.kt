package com.aadi.pixabaysample.screens.home

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.aadi.pixabaysample.R
import com.aadi.pixabaysample.toolkit.Utils
import com.aadi.pixabaysample.adapter.ImageResultAdapter
import com.aadi.pixabaysample.adapter.bindRecyclerView
import com.aadi.pixabaysample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import org.w3c.dom.Text

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

                    if(res.data.isNullOrEmpty()) showErrorLayout()
                    else bindRecyclerView(binding.recyclerView, res.data)

                }
            }
        }
    }

    private fun initLayout() {

        binding.sb.apply {
            etSearch.setOnFocusChangeListener { view, hasFocus ->
                toolbarLeftBtn.visibility = if(hasFocus) View.VISIBLE else View.GONE
            }
            toolbarLeftBtn.setOnClickListener {
                if(etSearch.text.isNotBlank()) etSearch.text.clear() else Utils.hideKeyboard(it.context, etSearch)
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
        }

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
