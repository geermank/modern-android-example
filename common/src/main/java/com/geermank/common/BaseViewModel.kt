package com.geermank.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onCoroutineError(throwable)
    }

    protected fun runCoroutine(task: suspend() -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            task()
        }
    }

    protected open fun onCoroutineError(error: Throwable) {
        // ignore error by default
    }
}
