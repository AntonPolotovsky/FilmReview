package com.example.testapplication.di

import com.example.testapplication.network.intersepter.InterceptorFactory
import com.example.testapplication.network.intersepter.MokResponseInterceptors
import com.example.testapplication.network.remotedatasource.api.AppApi
import com.example.testapplication.ret.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor


@InstallIn(SingletonComponent::class)
@Module
class NetWorkModule {



    @Provides
    fun provideHttpLoggingInterceptors():HttpLoggingInterceptor{
        return InterceptorFactory.createHttpLoggingInterceptor()
    }

    @Provides
    fun provideMokResponseInterceptor() : MokResponseInterceptors {
        return MokResponseInterceptors()
    }

}