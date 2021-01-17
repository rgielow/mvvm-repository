package com.picpay.desafio.android.di.module

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.di.provideApiService
import com.picpay.desafio.android.di.provideNetworkHelper
import com.picpay.desafio.android.di.provideOkHttpClient
import com.picpay.desafio.android.di.provideRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { provideOkHttpClient(androidContext(), get()) }
    single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
}
