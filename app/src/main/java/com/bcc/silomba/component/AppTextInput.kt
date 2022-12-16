package com.bcc.silomba.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bcc.silomba.R
import com.bcc.silomba.ui.style.AppColor
import com.bcc.silomba.ui.style.AppType

@Composable
fun AppTextInputNormal(
    modifier: Modifier = Modifier,
    contentSpacing: Dp = 4.dp,
    showWarningMessage: Boolean = false,
    warningMessage: String = "",
    label: String = "",
    placeHolder: String,
    textStyle: TextStyle = AppType.sm(),
    value: String,
    onValueChange: (String) -> Unit,
    shape: Shape = RoundedCornerShape(4.dp),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    textColor: Color = AppColor.Gray900,
    placeHolderColor: Color = AppColor.Gray300,
    disabledTextColor: Color = AppColor.Gray300,
    backgroundColor: Color = if (enabled) AppColor.White else AppColor.Gray200,
    cursorColor: Color = AppColor.Gray900,
    errorCursorColor: Color = AppColor.Error500,
    focusedIndicatorColor: Color = MaterialTheme.colors.primary,
    unfocusedIndicatorColor: Color = AppColor.Gray300,
    disabledIndicatorColor: Color = AppColor.Gray300,
    errorIndicatorColor: Color = AppColor.Error500
) {
    Column(verticalArrangement = Arrangement.spacedBy(contentSpacing)) {
        if (label.isNotEmpty()) {
            AppText(text = label, textType = AppTextType.Sm)
        }
        OutlinedTextField(
            modifier = modifier,
            shape = shape,
            readOnly = readOnly,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                textColor = textColor,
                disabledTextColor = disabledTextColor,
                backgroundColor = backgroundColor,
                cursorColor = cursorColor,
                errorCursorColor = errorCursorColor,
                focusedIndicatorColor = focusedIndicatorColor,
                unfocusedIndicatorColor = unfocusedIndicatorColor,
                disabledIndicatorColor = disabledIndicatorColor,
                errorIndicatorColor = errorIndicatorColor
            ),
            placeholder = {
                AppText(
                    text = placeHolder,
                    textType = AppTextType.Sm,
                    color = placeHolderColor
                )
            },
            textStyle = textStyle
        )

        if (showWarningMessage) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = rememberAsyncImagePainter(model = R.drawable.ic_alert),
                    contentDescription = "Alert icon",
                    tint = when {
                        isError -> AppColor.Error500
                        else -> AppColor.Gray500
                    }
                )
                AppText(
                    text = warningMessage,
                    textType = AppTextType.Xs,
                    color = when {
                        isError -> AppColor.Error500
                        else -> AppColor.Gray500
                    }
                )
            }
        }
    }
}