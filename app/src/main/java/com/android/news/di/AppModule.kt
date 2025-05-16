package com.android.news.di

import android.app.Application
import com.android.news.data.manger.LocalUserMangerImpl
import com.android.news.data.remote.NewsApi
import com.android.news.data.repository.NewsRepositoryImpl
import com.android.news.domain.manger.LocalUserManger
import com.android.news.domain.repository.NewsRepository
import com.android.news.domain.usercases.app_entry.AppEntryUseCases
import com.android.news.domain.usercases.app_entry.ReadAppEntry
import com.android.news.domain.usercases.app_entry.SaveAppEntry
import com.android.news.domain.usercases.news.GetNews
import com.android.news.domain.usercases.news.NewsUseCases
import com.android.news.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ) : LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun providesAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun ProvideNewsRepository(
        newsApi: NewsApi
    ) : NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ) : NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }
}