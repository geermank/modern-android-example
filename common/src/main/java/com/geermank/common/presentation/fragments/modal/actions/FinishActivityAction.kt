package com.geermank.common.presentation.fragments.modal.actions

import com.geermank.common.R
import com.geermank.common.presentation.fragments.modal.Modal
import kotlinx.android.parcel.Parcelize

@Parcelize
class FinishActivityAction : ModalAction {

    override fun uiText(): Int {
        return R.string.exit
    }

    override fun execute(modal: Modal) {
        modal.activity?.finish()
    }
}
