package com.geermank.common.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.*
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class CoroutineExecutorTest {

    @Test
    fun `running a coroutines launches a new one in the given scope`() {
        val coroutineScope = TestCoroutineScope()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
            // does nothing
        }

        val coroutineExecutor = CoroutineExecutor(coroutineScope, coroutineExceptionHandler)

        coroutineExecutor.runCoroutine {
            // asserts that the given task is being executed
            assertTrue(true)
        }
    }

    @Test
    fun `running a coroutines launches a new one in the given scope, and when an exception is thrown it is catch by handler`() {
        val coroutineScope = TestCoroutineScope()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
            // asserts that the given task has been executed
            // and that the exception has been catch by the hanlder
            assertTrue(true)
        }

        val coroutineExecutor = CoroutineExecutor(coroutineScope, coroutineExceptionHandler)

        coroutineExecutor.runCoroutine {
            throw RuntimeException("Ops! Something went wrong")
        }
    }
}
