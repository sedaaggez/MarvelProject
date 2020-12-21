package com.sedaaggez.marvelproject.model.character

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterResult(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val thumbnail: CharacterThumbnail?,
    val resourceURI: String?,
    val comics: Comic?,
    val series: CharacterSeries?,
    val stories: CharacterStory?,
    val events: CharacterEvent?,
    val urls: List<CharacterUrl>?
) : Parcelable {

    val imageUrl: String
        get() = String.format(
            "${thumbnail?.path}.${thumbnail?.extension}",
        )

}
