package com.picpay.desafio.android.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @Json(name = "img") val img: String,
    @Json(name = "name") val name: String,
    @Json(name = "id") val id: Int,
    @Json(name = "username") val username: String
) : Parcelable