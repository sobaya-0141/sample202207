package com.example.repository.exception

class SampleNetworkException(
    val statusCode: Int,
    val errorMessage: String
) : java.lang.Exception()