package com.example.showcat.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatApi(
    val id: String,
    val url: String
) : Parcelable