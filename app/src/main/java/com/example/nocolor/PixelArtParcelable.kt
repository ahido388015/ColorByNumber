package com.example.nocolor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PixelArtParcelable1(
    val title: String,
    val pixelData: Array<IntArray>,
    val colorMap: Map<Int, Int>
) : Parcelable
