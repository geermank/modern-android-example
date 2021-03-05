package com.geermank.data.api.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaginatedResponseDto<T : Parcelable>(
        val info: PaginatedResponseInfoDto,
        val results: List<T>
) : Parcelable

@Parcelize
data class PaginatedResponseInfoDto(
    val pages: Int
) : Parcelable

@Parcelize
data class ErrorResponse(
    val code: Int,
    val message: String
) : Parcelable
