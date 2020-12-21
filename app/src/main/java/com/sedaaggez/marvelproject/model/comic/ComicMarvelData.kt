package com.sedaaggez.marvelproject.model.comic

data class ComicMarvelData(
   val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val etag: String?,
    val data: ComicData?
)
