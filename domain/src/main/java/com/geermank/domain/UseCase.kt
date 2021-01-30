package com.geermank.domain

interface UseCase<Parameter, ReturnType> {
    fun execute(parameter: Parameter): ReturnType
}