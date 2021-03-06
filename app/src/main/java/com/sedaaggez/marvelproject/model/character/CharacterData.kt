package com.sedaaggez.marvelproject.model.character

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterData(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<CharacterResult>?
) : Parcelable
