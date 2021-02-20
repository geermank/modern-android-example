package com.geermank.domain

interface UseCase<Parameter, ReturnType> {
    suspend fun execute(parameter: Parameter): ReturnType
}