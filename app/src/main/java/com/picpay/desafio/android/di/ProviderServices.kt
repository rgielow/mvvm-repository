package com.picpay.desafio.android.di

import android.content.Context
import com.picpay.desafio.android.api.ApiHelper
import com.picpay.desafio.android.api.ApiHelperImpl
import com.picpay.desafio.android.api.NetworkHelper
import com.picpay.desafio.android.api.PicPayService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun provideNetworkHelper(context: Context) = NetworkHelper(context)

fun provideOkHttpClient(
    context: Context,
    networkHelper: NetworkHelper
): OkHttpClient {
    val cacheSize = (5 * 1024 * 1024).toLong()
    val myCache = Cache(context.cacheDir, cacheSize)

    return OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (networkHelper.isNetworkConnected())
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }
        .build()
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

fun provideApiService(retrofit: Retrofit): PicPayService =
    retrofit.create(PicPayService::class.java)

fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
