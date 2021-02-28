package com.geermank.common.presentation.fragments.modal.actions

import androidx.annotation.StringRes
import com.geermank.common.R
import com.geermank.common.presentation.fragments.modal.Modal
import kotlinx.android.parcel.Parcelize

@Parcelize
class DismissAction(@StringRes private val actionText: Int = R.string.ok) : ModalAction {

    override fun uiText(): Int {
        return actionText
    }

    override fun execute(modal: Modal) {
        modal.dismiss()
    }
}
