package com.android.news.di

import android.app.Application
import com.android.news.data.manger.LocalUserMangerImpl
import com.android.news.domain.manger.LocalUserManger
import com.android.news.domain.usercases.app_entry.AppEntryUseCases
import com.android.news.domain.usercases.app_entry.ReadAppEntry
import com.android.news.domain.usercases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}