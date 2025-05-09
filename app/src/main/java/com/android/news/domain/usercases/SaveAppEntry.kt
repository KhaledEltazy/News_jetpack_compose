package com.android.news.domain.usercases

import com.android.news.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger : LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }
}