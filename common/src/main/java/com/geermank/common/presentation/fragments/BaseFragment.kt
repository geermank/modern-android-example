package com.geermank.common.presentation.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.geermank.common.presentation.fragments.modal.Modal
import com.geermank.common.presentation.fragments.modal.ModalData

abstract class BaseFragment : Fragment() {

    private var fragmentInteractListener: FragmentInteractListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentInteractListener = context as FragmentInteractListener
    }

    protected fun showModal(modalData: ModalData) {
        showModal(Modal.TAG, modalData)
    }

    protected fun showModal(modalTag: String, modalData: ModalData) {
        fragmentInteractListener?.showModalFromFragment(tag, modalTag, modalData)
    }
}
