package com.picpay.desafio.android.di.module

import com.picpay.desafio.android.api.ApiHelper
import com.picpay.desafio.android.api.ApiHelperImpl
import com.picpay.desafio.android.repository.UserRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        UserRepository(get())
    }
    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

