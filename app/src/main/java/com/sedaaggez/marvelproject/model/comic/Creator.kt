package com.sedaaggez.marvelproject.model.comic

data class Creator(
    val available: Int?,
    val collectionURI: String?,
    val items: List<ComicItem>?,
    val returned: Int?
)
