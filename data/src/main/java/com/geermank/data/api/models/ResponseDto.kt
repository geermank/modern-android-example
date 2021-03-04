package com.geermank.data.api.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDto<T : Parcelable>(
    val info: ResponseInfoDto,
    val results: List<T>
) : Parcelable

@Parcelize
data class ResponseInfoDto(
    val pages: Int
) : Parcelable

@Parcelize
data class ErrorResponse(
    val code: Int,
    val message: String
) : Parcelable
