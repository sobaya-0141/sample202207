package com.example.usecase

import com.example.network.data.ListScreenReponse
import com.example.repository.ListRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class GetListDataUseCase @Inject constructor(
    private val listRepository: ListRepository
) {
    private val _listData = MutableSharedFlow<ListScreenReponse?>()
    val listData: SharedFlow<ListScreenReponse?> = _listData

    suspend operator fun invoke() {
        val data = listRepository.getListScreenData()
        _listData.emit(data)
    }
}