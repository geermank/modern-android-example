package com.geermank.common.presentation.fragments.modal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.geermank.common.R
import com.geermank.common.databinding.ModalBinding
import com.geermank.common.extensions.show
import com.geermank.common.presentation.fragments.BaseDialogFragment
import com.geermank.common.presentation.fragments.modal.actions.ModalAction

class Modal : BaseDialogFragment() {

    companion object {
        const val TAG = "Modal"
        private const val MODAL_DATA_KEY = "ERROR_CONFIG_KEY"

        fun newInstance(modalData: ModalData) = Modal().apply {
            arguments = Bundle().also { it.putParcelable(MODAL_DATA_KEY, modalData) }
        }
    }

    private lateinit var binding: ModalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.TransparentFullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ModalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getModalData().run {
            setIcon(icon)
            setTitle(title)
            setSubtitle(subtitle)
            setActions(primaryAction, secondaryAction)
            setIsCancelable(cancelable)
        }
    }

    private fun setIcon(icon: Int?) {
        icon?.let {
            binding.ivIcon.apply {
                show()
                setImageResource(it)
            }
        }
    }

    private fun setTitle(@StringRes title: Int) {
        binding.tvModalTitle.text = getString(title)
    }

    private fun setSubtitle(@StringRes subtitle: Int?) {
        subtitle?.let { binding.tvModalSubtitle.text = getString(it) }
    }

    private fun setActions(primaryAction: ModalAction, secondaryAction: ModalAction?) {
        binding.btnPositiveAction.apply {
            text = getString(primaryAction.uiText())
            setOnClickListener {
                primaryAction.execute(this@Modal)
            }
        }
        secondaryAction?.let {
            binding.btnNegativeAction.apply {
                text = getString(it.uiText())
                setOnClickListener { _ -> it.execute(this@Modal) }
            }
        }
    }

    private fun setIsCancelable(cancelable: Boolean) {
        isCancelable = cancelable
    }

    private fun getModalData(): ModalData = requireArguments().getParcelable(MODAL_DATA_KEY)!!
}
