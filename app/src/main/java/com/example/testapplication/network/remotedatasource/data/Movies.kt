package com.example.testapplication.network.remotedatasource.data

data class Movies(
    val status: String,
    val copyright: String,
    var has_more: Boolean,
    val num_results: Int,
    val results: List<Result>
    )