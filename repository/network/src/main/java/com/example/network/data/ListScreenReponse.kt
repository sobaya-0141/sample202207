package com.example.network.data

import kotlinx.serialization.Serializable

@Serializable
data class ListScreenReponse(
    val location: String,
    val chips: List<Chip>,
    val campaign: Campaign,
    val populars: List<Stores>,
    val perks: List<Stores>
)

@Serializable
data class Chip(
    val id: Int,
    val title: String
)

@Serializable
data class Stores(
    val imageUrl: String,
    val stars: String,
    val title: String,
    val time: String
)

@Serializable
data class Campaign(
    val title: String,
    val target: String
)