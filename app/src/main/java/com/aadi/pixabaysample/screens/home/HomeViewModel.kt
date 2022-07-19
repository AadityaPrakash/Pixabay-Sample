package com.aadi.pixabaysample.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aadi.pixabay.domain.models.ImagesModel
import com.aadi.pixabay.domain.usecases.GetSearchQueryResultsUseCase
import com.aadi.pixabay.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getSearchQueryResultsUseCase: GetSearchQueryResultsUseCase)
        : ViewModel() {

        private val _results = MutableStateFlow<HomeState>(HomeState())
        val results = _results.asStateFlow()

        init {
            getSearchQuery()
        }

        fun getSearchQuery() {
            getSearchQueryResultsUseCase().onEach {
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


}