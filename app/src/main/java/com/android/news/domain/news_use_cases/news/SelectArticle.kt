package com.android.news.domain.news_use_cases.news

import com.android.news.data.local.NewsDao
import com.android.news.domain.model.Article
import com.android.news.domain.repository.NewsRepository

class SelectArticle(
    val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url : String) : Article?{
       return newsRepository.selectArticle(url)
    }
}