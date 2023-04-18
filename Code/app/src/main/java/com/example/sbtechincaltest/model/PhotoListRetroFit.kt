package com.example.sbtechincaltest.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object PhotoListRetroFit {
    private fun createRetroFit(): Retrofit {
//        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
//        client.addInterceptor(interceptor)
        return Retrofit.Builder()
            .baseUrl(PhotoListService.API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client.build())
            .build()
    }

    fun createPhotoListService(): PhotoListService {
        return createRetroFit().create(PhotoListService::class.java)
    }

}