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
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
) : Parcelable
