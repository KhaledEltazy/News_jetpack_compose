package com.android.news.domain.news_use_cases.news

import com.android.news.data.local.NewsDao
import com.android.news.domain.model.Article
import com.android.news.domain.repository.NewsRepository

class UpsertArticle (
    val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}