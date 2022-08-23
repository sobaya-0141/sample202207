package com.example.repository

import com.example.network.data.ListScreenReponse

interface ListRepository {
    suspend fun getListScreenData(): ListScreenReponse?
}