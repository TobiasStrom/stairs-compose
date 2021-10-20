package com.tobiasstrom.stairs.common.utils

import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDuration

object CommonUtils {
    fun createSnackbarData(message: String, duration: SnackbarDuration? = null, actionText: String? = null): SnackbarData = object : SnackbarData {
        override val message: String = message
        override val duration = duration ?: SnackbarDuration.Short
        override val actionLabel = actionText
        override fun dismiss() { }
        override fun performAction() { }
    }

    fun SnackbarData.isEqual(other: SnackbarData): Boolean {
        return message == other.message &&
            duration == other.duration &&
            actionLabel == other.actionLabel
    }
}
