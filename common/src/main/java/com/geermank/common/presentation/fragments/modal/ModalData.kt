package com.geermank.common.presentation.fragments.modal

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.geermank.common.presentation.fragments.modal.actions.DismissAction
import com.geermank.common.presentation.fragments.modal.actions.ModalAction
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModalData(
    @DrawableRes val icon: Int?,
    @StringRes val title: Int,
    @StringRes val subtitle: Int?,
    val primaryAction: ModalAction = DismissAction(),
    val secondaryAction: ModalAction? = null,
    val cancelable: Boolean = true
) : Parcelable
