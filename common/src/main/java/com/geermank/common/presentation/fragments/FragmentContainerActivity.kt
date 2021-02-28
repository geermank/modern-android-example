package com.geermank.common.presentation.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.geermank.common.presentation.activities.BaseActivity
import com.geermank.common.presentation.fragments.modal.ModalData

abstract class FragmentContainerActivity : BaseActivity(), FragmentInteractListener {

    abstract val fragmentContainerId: Int

    fun addFragment(fragment: Fragment, tag: String, addToBackStack: Boolean = false) {
        supportFragmentManager.commit {
            addToBackStack(addToBackStack, this)
            add(fragmentContainerId, fragment, tag)
        }
    }

    fun replaceFragment(fragment: Fragment, tag: String, addToBackStack: Boolean) {
        supportFragmentManager.commit {
            addToBackStack(addToBackStack, this)
            replace(fragmentContainerId, fragment, tag)
        }
    }

    private fun addToBackStack(addToBackStack: Boolean, transaction: FragmentTransaction) {
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
    }

    override fun showModalFromFragment(requesterTag: String?, modalTag: String, modalData: ModalData) {
        showModal(modalTag, modalData)
    }
}
