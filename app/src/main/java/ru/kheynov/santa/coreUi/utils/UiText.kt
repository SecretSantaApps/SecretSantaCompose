package ru.kheynov.santa.coreUi.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class PlainText(val text: String) : UiText()
    class StringResource(@StringRes val resId: Int) : UiText()

    @Composable
    fun asString(): String =
        when (this) {
            is PlainText -> text
            is StringResource -> stringResource(id = resId)
        }

    fun asString(context: Context): String =
        when (this) {
            is PlainText -> text
            is StringResource -> context.getString(resId)
        }
}