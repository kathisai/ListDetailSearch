package com.sample.simpsonsviewer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Icon(
    val Height: String,
    val URL: String,
    val Width: String
) : Parcelable