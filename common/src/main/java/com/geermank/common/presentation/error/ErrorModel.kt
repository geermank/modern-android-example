package com.geermank.common.presentation.error

import androidx.annotation.StringRes

data class ErrorModel(
    @StringRes val title: Int,
    @StringRes val cause: Int?,
    val isRecoverable: Boolean
)