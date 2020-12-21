package com.sedaaggez.marvelproject.model.comic

data class ComicData(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<ComicResult>?
)
