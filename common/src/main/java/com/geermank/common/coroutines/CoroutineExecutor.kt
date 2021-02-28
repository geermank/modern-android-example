package com.geermank.common.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CoroutineExecutor(
    private val coroutineScope: CoroutineScope,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
) {

    fun runCoroutine(task: suspend () -> Unit) {
        coroutineScope.launch(coroutineExceptionHandler) {
            task()
        }
    }
}
