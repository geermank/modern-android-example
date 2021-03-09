package com.geermank.rickandmorty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class ViewModelTest {

    @Rule
    @JvmField
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()
}