package com.sedaaggez.marvelproject.model.comic

data class ComicResult(
    val id: Int,
    val digitalId: Int,
    val title: String,
    val issueNumber: Int,
    val variantDescription: String,
    val description: String,
    val modified: String,
    val isbn: String,
    val upc: String,
    val diamondCode: String,
    val ean: String,
    val issn: String,
    val format: String,
    val pageCount: Int,
    val textObjects: List<TextObject>,
    val resourceURI: String,
    val urls: List<ComicUrl>,
    val series: ComicSeries,
    val variants: List<String>,
    val collections: List<Any>,
    val collectedIssues: List<String>,
    val dates: List<Date>,
    val prices: List<Price>,
    val thumbnail: ComicThumbnail,
    val images: List<Image>,
    val creators: Creator,
    val characters: Character,
    val stories: ComicStory,
    val events: ComicEvent
)
