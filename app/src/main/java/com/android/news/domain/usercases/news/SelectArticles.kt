package com.android.news.domain.usercases.news

import com.android.news.data.local.NewsDao
import com.android.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    val newsDao: NewsDao
) {
    operator fun invoke() : Flow<List<Article>> {
        return newsDao.getArticles()
    }
}