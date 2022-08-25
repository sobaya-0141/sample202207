package com.example.repository

import com.example.network.data.ListScreenReponse
import com.example.network.list.ListService
import com.example.repository.exception.SampleNetworkException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ListRepositoryImpl @Inject constructor(
    private val listService: ListService
) : ListRepository {
    override fun getListScreenData(): Flow<ListScreenReponse> =
        flow {
            val response = listService.getListScreenData()
            if (response.isSuccessful) {
                emit(response.body()!!)
            } else {
                throw SampleNetworkException(
                    statusCode = response.code(),
                    errorMessage = response.message()
                )
            }
        }
}