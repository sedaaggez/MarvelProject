package com.sedaaggez.marvelproject.model.character

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterItem(
    val resourceURI: String?,
    val name: String?,
    val type: String?
) : Parcelable
