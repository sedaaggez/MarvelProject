package com.sedaaggez.marvelproject.model.character

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterSeries(
    val available: Int?,
    val collectionURI: String?,
    val items: List<CharacterItem>?,
    val returned: Int?
) : Parcelable
