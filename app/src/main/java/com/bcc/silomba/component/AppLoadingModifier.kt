package com.bcc.silomba.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

object AppLoadingModifier {
    val textLoadingModifier = Modifier.placeholder(
        visible = true,
        color = Color.LightGray,
        shape = RoundedCornerShape(4.dp),
        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
    )

    val rectLoadingModifier = Modifier.placeholder(
        visible = true,
        color = Color.LightGray,
        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
    )

    val circleLoadingModifier = Modifier.placeholder(
        visible = true,
        color = Color.LightGray,
        shape = CircleShape,
        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
    )
}