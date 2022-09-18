package com.example.listscreen

import com.example.network.data.Campaign
import com.example.network.data.Chip
import com.example.network.data.ListScreenReponse
import com.example.network.data.Stores
import com.example.test_core.util.MainDispatcherRule
import com.example.usecase.GetListDataUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ListViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Mock
    private val getListDataUseCase: GetListDataUseCase = mock()
    private lateinit var listViewModel: ListViewModel

    @Test
    fun uiState_Success() = runTest {
        val flow: Flow<ListScreenReponse> = flow {
            emit(testListScreenData)
        }
        whenever(getListDataUseCase.invoke()).thenReturn(flow)
        listViewModel = ListViewModel(getListDataUseCase)
        assert(!listViewModel.uiState.isLoading)
        assert(!listViewModel.uiState.isError)
    }

    @Test
    fun uiState_Error() = runTest {
        val flow: Flow<ListScreenReponse> = flow {
            throw Exception("")
        }
        whenever(getListDataUseCase.invoke()).thenReturn(flow)
        listViewModel = ListViewModel(getListDataUseCase)
        assert(!listViewModel.uiState.isLoading)
        assert(listViewModel.uiState.isError)
    }

    private val testListScreenData = ListScreenReponse(
        location = "Location",
        chips = listOf(Chip(1, "title")),
        campaign = Campaign("title", "target"),
        populars = listOf(Stores("", "2", "title", "time")),
        perks = listOf(Stores("", "2", "title", "time"))
    )
}

