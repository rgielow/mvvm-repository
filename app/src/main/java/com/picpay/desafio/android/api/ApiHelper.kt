package com.picpay.desafio.android.api

import com.picpay.desafio.android.model.User
import retrofit2.Response
interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>
}