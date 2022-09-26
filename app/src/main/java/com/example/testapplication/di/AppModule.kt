package com.example.testapplication.di

import com.example.testapplication.network.localdatasource.MokData
import com.example.testapplication.network.remotedatasource.api.AppApi
import com.example.testapplication.network.remotedatasource.api.AppRemoteRepository
import com.example.testapplication.network.remotedatasource.api.AppRemoteRepositoryImpl
import com.example.testapplication.network.remotedatasource.api.MoviesSource
import com.example.testapplication.presentation.filmactivity.domain.FilmPageInteractor
import com.example.testapplication.presentation.filmactivity.domain.FilmPageInteractorImpl
import com.example.testapplication.ret.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideFilmPageInteractor(mokData: MokData,appRemoteRepository: AppRemoteRepository): FilmPageInteractor {
        return FilmPageInteractorImpl(mokData,appRemoteRepository)
    }

    @Provides
    fun provideMokData(): MokData {
        return MokData()
    }

    @Provides
    fun providesRemoteRepository(appApi: AppApi):AppRemoteRepository{
        return AppRemoteRepositoryImpl(appApi)
    }

    @Provides
    fun provideRetrofitFactory():RetrofitFactory{
        return RetrofitFactory()
    }

    @Provides
    fun provideAppApi(
        retrofitFactory: RetrofitFactory
    ):AppApi{
        return retrofitFactory.getApiInterface()
    }

}