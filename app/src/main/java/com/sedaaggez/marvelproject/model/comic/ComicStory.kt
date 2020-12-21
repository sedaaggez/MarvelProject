package com.sedaaggez.marvelproject.model.comic

data class ComicStory(
    val available: Int?,
    val collectionURI: String?,
    val items: List<ComicItem>?,
    val returned: Int?
)
