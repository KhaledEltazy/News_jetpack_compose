package com.android.news.domain.news_use_cases.news

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