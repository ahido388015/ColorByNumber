package com.example.nocolor

import android.graphics.Color

data class PixelArt(
    val id: Int,
    val title: String,
    val category: String,
    val pixelData: Array<IntArray>,
    val colors: List<Int>
)

object PixelArtData {
    val pixelArts = listOf(
        PixelArt(
            id = 1,
            title = "Flower",
            category = "Nature",
            pixelData = arrayOf(
                intArrayOf(0, 0, 1, 1, 1, 0, 0),
                intArrayOf(0, 1, 2, 2, 2, 1, 0),
                intArrayOf(1, 2, 3, 3, 3, 2, 1),
                intArrayOf(1, 2, 3, 4, 3, 2, 1),
                intArrayOf(1, 2, 3, 3, 3, 2, 1),
                intArrayOf(0, 1, 2, 2, 2, 1, 0),
                intArrayOf(0, 0, 1, 1, 1, 0, 0)
            ),
            colors = listOf(Color.RED, Color.BLUE,Color.YELLOW, Color.GREEN)
        ),
        PixelArt(
            id = 2,
            title = "Smiley Face",
            category = "Simple",
            pixelData = arrayOf(
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0),
                intArrayOf(0, 0, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 1, 0, 0),
                intArrayOf(0, 0, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 1, 0, 0),
                intArrayOf(0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0),
                intArrayOf(0, 0, 1, 2, 2, 1, 2, 2, 2, 1, 2, 2, 1, 0, 0),
                intArrayOf(0, 0, 0, 1, 2, 2, 1, 1, 1, 2, 2, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            ),
            colors = listOf(Color.BLACK, Color.YELLOW)
        ),
        PixelArt(
            id = 3,
            title = "Heart",
            category = "Simple",
            pixelData = arrayOf(
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0),
                intArrayOf(0, 0, 1, 2, 2, 1, 0, 1, 4, 3, 1, 0, 0),
                intArrayOf(0, 1, 2, 2, 3, 3, 1, 4, 3, 3, 2, 1, 0),
                intArrayOf(0, 1, 2, 3, 3, 4, 4, 3, 3, 2, 2, 1, 0),
                intArrayOf(0, 0, 1, 3, 4, 4, 3, 3, 2, 2, 1, 0, 0),
                intArrayOf(0, 0, 0, 1, 4, 3, 3, 2, 2, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 3, 2, 2, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            ),
            colors = listOf(Color.BLACK, Color.RED, Color.BLUE, Color.MAGENTA)
        ),
        PixelArt(
            id = 4,
            title = "Flower Blue",
            category = "Nature",
            pixelData = arrayOf(
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 1, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 1, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 1, 1, 3, 3, 1, 1, 1, 0, 0, 0),
                intArrayOf(0, 0, 1, 3, 3, 1, 3, 3, 1, 3, 3, 1, 0, 0),
                intArrayOf(0, 0, 1, 3, 3, 3, 1, 1, 3, 3, 3, 1, 0, 0),
                intArrayOf(0, 0, 1, 1, 3, 1, 4, 4, 1, 3, 1, 1, 0, 0),
                intArrayOf(0, 1, 3, 3, 1, 4, 4, 4, 4, 1, 3, 3, 1, 0),
                intArrayOf(0, 1, 3, 3, 1, 4, 4, 4, 4, 1, 3, 3, 1, 0),
                intArrayOf(0, 0, 1, 1, 3, 1, 4, 4, 1, 3, 1, 1, 0, 0),
                intArrayOf(0, 0, 1, 3, 3, 3, 1, 1, 3, 3, 3, 1, 0, 0),
                intArrayOf(0, 0, 1, 3, 3, 1, 3, 3, 1, 3, 3, 1, 0, 0),
                intArrayOf(0, 0, 0, 1, 1, 1, 3, 3, 1, 1, 1, 1, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 1, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 1, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                ),
            colors = listOf(
                Color.BLACK,                 // 0
                Color.rgb(0, 100, 0),        // 1 - Green (dark)
                Color.rgb(100, 120, 237),    // 2 - Blue (soft)
                Color.rgb(255, 140, 0),     // 3 - Yellow (deep)
            )

        ),

        PixelArt(
            id = 5,
            title = "BunnieCuttie",
            category = "Animal",
            pixelData = arrayOf(
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 1, 3, 3, 1, 3, 3, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 3, 3, 1, 3, 3, 3, 3, 3, 1, 3, 3, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 3, 3, 1, 2, 2, 1, 2, 2, 1, 3, 3, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                ),
            colors = listOf(Color.BLACK, Color.WHITE, Color.DKGRAY)
        ),
        PixelArt(
            id = 6,
            title = "Flamingo",
            category = "Animal",
            pixelData = arrayOf(
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 1, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 3, 3, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 3, 3, 2, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 2, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 1, 1, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 1, 2, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0),
                ),
            colors = listOf(Color.rgb(255, 105, 180), Color.BLACK, Color.YELLOW)
        ),
        PixelArt(
            id = 7,
            title = "Baby Duck",
            category = "Animal",
            pixelData = arrayOf(
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 1, 1, 0, 0, 0),
                intArrayOf(0, 0, 1, 1, 1, 1, 1, 0, 0),
                intArrayOf(0, 0, 1, 2, 1, 2, 1, 0, 0),
                intArrayOf(0, 1, 1, 3, 3, 3, 1, 1, 0),
                intArrayOf(0, 1, 1, 1, 3, 1, 1, 1, 0),
                intArrayOf(0, 0, 1, 1, 1, 1, 1, 0, 0),
                intArrayOf(0, 0, 0, 3, 0, 3, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            ),
            colors = listOf(Color.CYAN, Color.BLACK, 0xFFFFEB3B.toInt())
        ),
        PixelArt(
            id = 8,
            title = "Red Cherry",
            category = "Fruit",
            pixelData = arrayOf(
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 1, 2, 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 1, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 1, 1, 2, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 2, 1, 5, 1, 4, 4, 4, 4, 1, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 1, 2, 1, 4, 4, 4, 4, 4, 4, 1, 0),
                intArrayOf(0, 0, 0, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 1, 0),
                intArrayOf(0, 0, 1, 4, 4, 4, 4, 4, 4, 1, 4, 4, 3, 4, 1, 0),
                intArrayOf(0, 0, 1, 4, 4, 4, 4, 4, 4, 1, 3, 3, 4, 4, 1, 0),
                intArrayOf(0, 0, 1, 4, 4, 4, 4, 3, 4, 1, 4, 4, 4, 1, 0, 0),
                intArrayOf(0, 0, 1, 4, 4, 3, 3, 4, 4, 1, 1, 1, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 4, 4, 4, 4, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            ),
            colors = listOf(
                Color.BLACK,
                Color.rgb(0, 100, 0),
                Color.rgb(139, 0, 0),
                Color.rgb(200, 0, 0),
                Color.WHITE
            )
        ),
        PixelArt(
            id = 9,
            title = "Duck Lady",
            category = "Animal",
            pixelData = arrayOf(
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 2, 1, 1, 2, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 1, 3, 3, 1, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 1, 3, 3, 3, 3, 1, 1, 0, 0, 0),
                intArrayOf(0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0),
                intArrayOf(0, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 0),
                intArrayOf(0, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 0),
                intArrayOf(0, 1, 4, 4, 1, 1, 1, 1, 1, 1, 4, 4, 1, 0),
                intArrayOf(0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0),
                intArrayOf(0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 3, 3, 3, 0, 3, 3, 3, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            ),
            colors = listOf( Color.YELLOW,
                Color.BLACK,
                Color.rgb(255, 140, 0),
                Color.LTGRAY          )
        ),
        PixelArt(
            id = 10,
            title = "Fresh Lemon",
            category = "Fruit",
            pixelData = arrayOf(
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 1, 2, 2, 2, 1, 0, 0),
                intArrayOf( 0, 0, 0, 1, 3, 3, 1, 2, 2, 1, 0),
                intArrayOf( 0, 0, 1, 3, 3, 4, 3, 1, 2, 1, 0),
                intArrayOf( 0, 1, 5, 3, 3, 3, 4, 3, 1, 0, 0),
                intArrayOf( 0, 1, 5, 3, 3, 3, 3, 3, 1, 0, 0),
                intArrayOf( 0, 1, 5, 3, 4, 3, 3, 3, 1, 0, 0),
                intArrayOf( 0, 0, 1, 5, 3, 3, 5, 1, 0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 6, 6, 1, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),

                ),
            colors = listOf(// 0 - Transparent / no color (ignored in drawing)
                Color.rgb(0, 0, 0),           // 1 - Black
                Color.rgb(0, 128, 0),         // 2 - Dark Green
                Color.rgb(255, 255, 0),       // 3 - Yellow
                Color.rgb(255, 255, 255),     // 4 - White
                Color.rgb(255, 140, 50),       // 5 - Darker Yellow
                Color.rgb(255, 140, 0),        // 6 - Even Darker Yellow
            )
        ),
        PixelArt(
            id = 11,
            title = "Crayon Shin-chan",
            category = "Cartoon",
            pixelData = arrayOf(
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 1, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 1, 1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 1, 2, 2, 1, 2, 2, 2, 1, 1, 2, 2, 2, 1, 1, 2, 2, 2, 1, 0, 0, 0, 0),
                intArrayOf( 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0),
                intArrayOf( 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 1, 0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 3, 3, 1, 2, 2, 2, 2, 1, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 1, 3, 3, 1, 2, 2, 2, 1, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 1, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 1, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 1, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 1, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 1, 4, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 1, 2, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 2, 1, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 1, 5, 5, 5, 5, 1, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 1, 6, 6, 1, 0, 0, 1, 6, 6, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0),
            ),
            colors = listOf(
                Color.rgb(0, 0, 0),           // BLACK
                Color.rgb(255, 224, 189),     // WHITE → màu da
                Color.rgb(255, 10, 20),         // RED
                Color.rgb(10, 0, 255),         // giả sử màu 3 là BLUE
                Color.rgb(255, 255, 0),       // YELLOW
                Color.rgb(128, 64, 0)         // thêm màu cho chỉ số 5, ví dụ nâu đậm (cho giày/hair)
            )
        ),
        PixelArt(
            id = 13,
            title = "Adventure Time",
            category = "Cartoon",
            pixelData = arrayOf(
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 2, 2, 3, 3, 3, 3, 3, 3, 2, 2, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 2, 3, 1, 3, 3, 3, 3, 1, 3, 2, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 2, 3, 1, 3, 3, 3, 3, 1, 3, 2, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 2, 2, 3, 3, 3, 3, 3, 3, 2, 2, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 4, 2, 2, 2, 2, 2, 2, 2, 2, 4, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 1, 3, 3, 5, 4, 4, 4, 4, 4, 4, 5, 3, 3 ,1, 0, 0),
                intArrayOf( 0, 0, 1, 3, 3, 6, 4, 4, 4, 4, 4, 4, 6, 3, 3 ,1, 0, 0),
                intArrayOf( 0, 0, 0, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 1, 7, 7, 7, 7, 7, 7, 7, 7, 1, 0 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 1, 3, 3, 3, 1, 1, 3, 3, 3, 1, 0 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1 ,0, 0, 0),
                intArrayOf( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0),

                ),
            colors = listOf(
                Color.rgb(0, 0, 0),                 // BLACK
                Color.rgb(255, 255, 255),           // WHITE
                Color.rgb(255, 224, 189),           // Skin color
                Color.rgb(100, 100, 255),           // Lighter blue (softer than normal blue)
                Color.rgb(0, 0, 255),               // BLUE
                Color.rgb(0, 128, 0),               // GREEN
                Color.rgb(255, 235, 59)             // YELLOW (was 0xFFFFEB3B)
            )

        ),
    )

    fun getCategories(): List<String> {
        return pixelArts.map { it.category }.distinct()
    }

    fun getPixelArtsByCategory(category: String): List<PixelArt> {
        return pixelArts.filter { it.category == category }
    }
}