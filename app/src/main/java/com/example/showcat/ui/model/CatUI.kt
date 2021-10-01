package com.example.showcat.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CatUI(
    val id: String,
    val imageUrl: String
): Parcelable
