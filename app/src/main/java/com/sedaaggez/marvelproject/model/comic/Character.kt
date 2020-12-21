package com.sedaaggez.marvelproject.model.comic

data class Character(
    val available: Int?,
    val collectionURI: String?,
    val items: List<ComicItem>?,
    val returned: Int?
)
