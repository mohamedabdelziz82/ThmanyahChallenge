package com.mohamedabdelaziz.feature.home.presentation.ui.manipulator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.feature.home.domain.repository.HomeSectionsRepository
import com.mohamedabdelaziz.feature.home.domain.repository.SearchSectionsRepository
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeSection
import com.mohamedabdelaziz.feature.home.presentation.mapper.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: Mohamed Abdelaziz
 * @since Sat, 14 Jun 2025
 */
@HiltViewModel
  class HomeScreenViewModel @Inject constructor(
    private val getHomeSectionsRepository: HomeSectionsRepository,
    private val searchSectionsRepository: SearchSectionsRepository
) : ViewModel() {
    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> get() = _state

    private val _sections = MutableStateFlow<PagingData<UIHomeSection>>(PagingData.empty())
    val sections: StateFlow<PagingData<UIHomeSection>> = _sections

    init {
        getHomeSections()
    }

    private fun getHomeSections() {
        viewModelScope.launch {
            getHomeSectionsRepository.getHomeSections()
                .cachedIn(viewModelScope)
                .map { pagingData ->
                    pagingData.map { it.toUI() }
                }
                .onStart {
                    _state.value = _state.value.copy(isLoading = true)
                }
                .catch { error ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
                .collectLatest { uiPagingData ->
                    _state.value = _state.value.copy(isLoading = false)
                    _sections.value = uiPagingData
                }
        }
    }


    fun searchSections(query: String) {
        viewModelScope.launch {
            searchSectionsRepository.search(query)
                .onStart {
                    _state.value = _state.value.copy(
                        isLoading = true,
                        errorMessage = null
                    )
                }
                .catch { error ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
                .collect { result ->
                    when (result) {
                        is NetworkResult.Loading -> {
                            _state.value = _state.value.copy(
                                isLoading = true,
                                errorMessage = null
                            )
                        }

                        is NetworkResult.Success -> {
                            val uiResponse = result.data.toUI()
                            _state.value = _state.value.copy(
                                isLoading = false,
                                uiSearchSectionsResponse = uiResponse,
                                errorMessage = null
                            )
                        }

                        is NetworkResult.Error -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                }
        }
    }


}