package com.geermank.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geermank.common.coroutines.CoroutineExecutor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onCoroutineError(throwable)
    }

    protected val coroutineExecutor = CoroutineExecutor(viewModelScope, coroutineExceptionHandler)

    protected open fun onCoroutineError(error: Throwable) {
        // ignore error by default, let every subclass implement its behaviour
    }
}
