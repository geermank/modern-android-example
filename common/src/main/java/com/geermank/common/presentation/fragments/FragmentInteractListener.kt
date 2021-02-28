package com.geermank.common.presentation.fragments

import com.geermank.common.presentation.fragments.modal.ModalData

interface FragmentInteractListener {
    fun showModalFromFragment(requesterTag: String?, modalTag: String, modalData: ModalData)
}