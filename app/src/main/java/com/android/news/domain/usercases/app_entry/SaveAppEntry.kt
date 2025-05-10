package com.android.news.domain.usercases.app_entry

import com.android.news.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger : LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }
}