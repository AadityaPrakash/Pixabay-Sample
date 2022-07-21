package com.aadi.pixabaysample.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aadi.pixabay.domain.usecases.GetDefaultKeywordResultUseCase
import com.aadi.pixabay.domain.usecases.GetSearchQueryResultsUseCase
import com.aadi.pixabay.domain.utils.Resource
import com.aadi.pixabaysample.toolkit.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDefaultKeywordResultUseCase: GetDefaultKeywordResultUseCase,
    private val getSearchQueryResultsUseCase: GetSearchQueryResultsUseCase
) : ViewModel() {

    private val _results = MutableStateFlow(HomeState())
    val results: StateFlow<HomeState> = _results

    init {
        getDefaultSearchResults()
    }

    private fun getDefaultSearchResults() {
        getDefaultKeywordResultUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _results.value = HomeState(isLoading = true)
                }
                is Resource.Success -> {
                    _results.value = HomeState(data = it.data)
                }
                is Resource.Error -> {
                    _results.value = HomeState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSearchQueryResults(query: String) {
        getSearchQueryResultsUseCase(query).onEach {
            when (it) {
                is Resource.Loading -> {
                    _results.value = HomeState(isLoading = true)
                }
                is Resource.Success -> {
                    _results.value = HomeState(data = it.data)
                }
                is Resource.Error -> {
                    _results.value = HomeState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchQuery(mContext: Context, query: String) {
        if(Utils.isValidQuery(mContext, query))
            getSearchQueryResults(query)
    }
}