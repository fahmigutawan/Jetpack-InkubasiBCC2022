package com.bcc.silomba.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.bcc.silomba.ui.style.AppColor
import com.bcc.silomba.ui.style.AppType

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppColor.Black,
    textAlign: TextAlign? = TextAlign.Start,
    textType: AppTextType
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        style = textType.style
    )
}

@Composable
fun AppTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = AppColor.Black,
    rippleColor: Color = AppColor.Black,
    textAlign: TextAlign? = TextAlign.Start,
    textType: AppTextType
) {
    Text(
        text = text,
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(color = rippleColor),
            onClick = onClick
        ),
        color = color,
        textAlign = textAlign,
        style = textType.style
    )
}

enum class AppTextType(
    val style: TextStyle
) {
    Xs(
        AppType.xs()
    ),
    XsSemibold(
        AppType.xsSemibold()
    ),
    Sm(
        AppType.sm()
    ),
    SmSemibold(
        AppType.smSemibold()
    ),
    Md(
        AppType.md()
    ),
    MdSemibold(
        AppType.mdSemibold()
    ),
    Lg(
        AppType.lg()
    ),
    LgSemibold(
        AppType.lgSemibold()
    ),
    Xl(
        AppType.xl()
    ),
    XlSemibold(
        AppType.xlSemibold()
    )
}