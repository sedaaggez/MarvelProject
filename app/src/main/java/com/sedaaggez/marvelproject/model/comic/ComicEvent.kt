package com.sedaaggez.marvelproject.model.comic

data class ComicEvent(
    val available: Int?,
    val collectionURI: String?,
    val items: List<ComicItem>?,
    val returned: Int?
)
