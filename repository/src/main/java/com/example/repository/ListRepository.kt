package com.example.repository

import com.example.network.data.ListScreenReponse
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    fun getListScreenData(): Flow<ListScreenReponse>
}