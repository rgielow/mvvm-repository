package com.picpay.desafio.android.repository

import com.picpay.desafio.android.api.ApiHelper

class UserRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}