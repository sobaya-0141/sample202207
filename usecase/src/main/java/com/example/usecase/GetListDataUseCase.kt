package com.example.usecase

import com.example.network.data.ListScreenReponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface GetListDataUseCase {
    operator fun invoke(): Flow<ListScreenReponse>
}