package com.bcc.silomba.ui.style

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.bcc.silomba.R

object AppType {
    fun xs() = TextStyle(
        fontFamily = FontFamily(Font(R.font.worksans_regular)),
        fontSize = 12.sp
    )

    fun xsSemibold() = TextStyle(
        fontFamily = FontFamily(Font(R.font.worksans_semibold)),
        fontSize = 12.sp
    )

    fun sm() = TextStyle(
        fontFamily = FontFamily(Font(R.font.worksans_regular)),
        fontSize = 14.sp
    )

    fun smSemibold() = TextStyle(
        fontFamily = FontFamily(Font(R.font.worksans_semibold)),
        fontSize = 14.sp
    )

    fun md() = TextStyle(
        fontFamily = FontFamily(Font(R.font.worksans_regular)),
        fontSize = 16.sp
    )

    fun mdSemibold() = TextStyle(
        fontFamily = FontFamily(Font(R.font.worksans_semibold)),
        fontSize = 16.sp
    )

    fun lg() = TextStyle(
        fontFamily = FontFamily(Font(R.font.worksans_regular)),
        fontSize = 18.sp
    )

    fun lgSemibold() = TextStyle(
        fontFamily = FontFamily(Font(R.font.worksans_semibold)),
        fontSize = 18.sp
    )

    fun xl() = TextStyle(
        fontFamily = FontFamily(Font(R.font.worksans_regular)),
        fontSize = 20.sp
    )

    fun xlSemibold() = TextStyle(
        fontFamily = FontFamily(Font(R.font.worksans_semibold)),
        fontSize = 20.sp
    )
}