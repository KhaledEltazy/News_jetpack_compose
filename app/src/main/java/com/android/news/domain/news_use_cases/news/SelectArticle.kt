package com.android.news.domain.news_use_cases.news

import com.android.news.data.local.NewsDao
import com.android.news.domain.model.Article

class SelectArticle(
    val newsDao: NewsDao
) {
    suspend operator fun invoke(url : String) : Article?{
       return newsDao.getArticle(url)
    }
}