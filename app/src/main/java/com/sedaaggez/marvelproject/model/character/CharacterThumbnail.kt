package com.sedaaggez.marvelproject.model.character

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterThumbnail(
    val path: String?,
    val extension: String?
) : Parcelable
