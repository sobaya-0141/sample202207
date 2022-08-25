package com.example.usecase

import com.example.network.data.ListScreenReponse
import com.example.repository.ListRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GetListDataUseCaseImpl @Inject constructor(
    private val listRepository: ListRepository
) : GetListDataUseCase {
    override fun invoke(): Flow<ListScreenReponse> =
        listRepository.getListScreenData()
}