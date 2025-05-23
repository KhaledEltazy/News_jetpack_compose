package com.android.news.di

import android.app.Application
import androidx.room.Room
import com.android.news.data.local.NewsDao
import com.android.news.data.local.NewsDataBase
import com.android.news.data.local.NewsTypeConverter
import com.android.news.data.manger.LocalUserMangerImpl
import com.android.news.data.remote.NewsApi
import com.android.news.data.repository.NewsRepositoryImpl
import com.android.news.domain.manger.LocalUserManger
import com.android.news.domain.repository.NewsRepository
import com.android.news.domain.news_use_cases.app_entry.AppEntryUseCases
import com.android.news.domain.news_use_cases.app_entry.ReadAppEntry
import com.android.news.domain.news_use_cases.app_entry.SaveAppEntry
import com.android.news.domain.news_use_cases.news.DeleteArticle
import com.android.news.domain.news_use_cases.news.SelectArticle
import com.android.news.domain.news_use_cases.news.GetNews
import com.android.news.domain.news_use_cases.news.NewsUseCases
import com.android.news.domain.news_use_cases.news.SearchNews
import com.android.news.domain.news_use_cases.news.SelectArticles
import com.android.news.domain.news_use_cases.news.UpsertArticle
import com.android.news.util.Constants.BASE_URL
import com.android.news.util.Constants.DATABASE_NAME
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
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ) : NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao),
            selectArticle = SelectArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun providesNewsDataBase(
        application: Application
    ) : NewsDataBase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDataBase: NewsDataBase
    ) : NewsDao = newsDataBase.newsDao
}