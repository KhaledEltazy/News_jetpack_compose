package com.android.news.domain.usercases.news

import com.android.news.data.local.NewsDao
import com.android.news.domain.model.Article

class DeleteArticle(
    val newsDao: NewsDao
) {
    suspend operator fun invoke (article: Article){
        newsDao.delete(article)
    }
}