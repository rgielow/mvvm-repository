package com.picpay.desafio.android.api

import com.picpay.desafio.android.model.User
import retrofit2.Response

class ApiHelperImpl(private val picPayService: PicPayService) : ApiHelper {
    override suspend fun getUsers(): Response<List<User>> = picPayService.getUsers()
}