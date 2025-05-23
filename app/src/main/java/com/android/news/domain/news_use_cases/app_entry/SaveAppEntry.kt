package com.android.news.domain.news_use_cases.app_entry

import com.android.news.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger : LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }
}