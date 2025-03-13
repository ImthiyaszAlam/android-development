package com.imthiyas.paging3.di

import com.imthiyas.paging3.retrofit.QuotesAPI
import com.imthiyas.paging3.utils.ApiConstant.BASE_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun getQuoteAPI(retrofit: Retrofit): QuotesAPI {
        return retrofit.create(QuotesAPI::class.java)
    }

}