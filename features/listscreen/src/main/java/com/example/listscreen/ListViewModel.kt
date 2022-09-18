package com.example.listscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.data.ListScreenReponse
import com.example.usecase.GetListDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sobaya.libs.util.Result
import sobaya.libs.util.RetryTrigger
import sobaya.libs.util.asResult
import sobaya.libs.util.retryableFlow

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getListDataUseCase: GetListDataUseCase
) : ViewModel() {
    var uiState by mutableStateOf(ListState.initialState())
        private set
    private val retryTrigger = RetryTrigger()
    private val listData = retryableFlow(retryTrigger) {
        getListDataUseCase().asResult()
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null
    )

    init {
        observeApi()
    }

    fun getListData() {
        viewModelScope.launch {
            retryTrigger.retry()
        }
    }

    private fun observeApi() {
        listData.onEach {
            uiState = when (it) {
                is Result.Success -> {
                    uiState.copy(isLoading = false, data = it.data)
                }
                is Result.Loading -> {
                    uiState.copy(isLoading = true, isError = false, data = null)
                }
                is Result.Error -> {
                    uiState.copy(isLoading = false, isError = true, data = null)
                }
                else -> {
                    throw IllegalStateException()
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class ListState(
    val isError: Boolean,
    val isLoading: Boolean,
    val data: ListScreenReponse?
) {
    companion object {
        fun initialState(): ListState = ListState(
            isError = false,
            isLoading = true,
            data = null
        )
    }
}