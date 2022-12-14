package com.example.testapplication.network.remotedatasource.data



data class Result(
    val byline: String,
    val critics_pick: Float,
    val date_updated: String,
    val display_title: String,
    val headline: String,
    val link: Link,
    val mpaa_rating: String,
    val multimedia: Multimedia,
    val opening_date: String?,
    val publication_date: String,
    val summary_short: String
)