package com.geermank.common.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.geermank.common.presentation.fragments.modal.Modal
import com.geermank.common.presentation.fragments.modal.ModalData

abstract class BaseActivity : AppCompatActivity() {

    protected fun showModal(modalData: ModalData) {
        showModal(Modal.TAG, modalData)
    }

    protected fun showModal(tag: String, modalData: ModalData) {
        Modal.newInstance(modalData).show(supportFragmentManager, tag)
    }
}
