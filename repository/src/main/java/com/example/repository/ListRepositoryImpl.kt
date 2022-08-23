package com.example.repository

import com.example.network.data.ListScreenReponse
import com.example.network.list.ListService
import com.example.repository.exception.SampleNetworkException
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val listService: ListService
) : ListRepository {
    override suspend fun getListScreenData(): ListScreenReponse? {
        val response = listService.getListScreenData()
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw SampleNetworkException(
                statusCode = response.code(),
                errorMessage = response.message()
            )
        }
    }
}