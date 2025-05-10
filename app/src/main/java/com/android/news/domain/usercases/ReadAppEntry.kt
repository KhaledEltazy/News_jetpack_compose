package com.android.news.domain.usercases

import com.android.news.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger : LocalUserManger
) {
    operator fun invoke() : Flow<Boolean> {
        return localUserManger.readAppEntry()
    }
}