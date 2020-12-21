package com.sedaaggez.marvelproject.model.character

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterUrl(
    val type: String?,
    val url: String?
) : Parcelable
