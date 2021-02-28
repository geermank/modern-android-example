package com.geermank.common.presentation.fragments.modal.actions

import android.os.Parcelable
import androidx.annotation.StringRes
import com.geermank.common.presentation.fragments.modal.Modal

interface ModalAction : Parcelable {
    fun uiText(): Int
    fun execute(modal: Modal)
}
