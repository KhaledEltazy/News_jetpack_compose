package com.android.news.domain.usercases.news

import androidx.paging.PagingData
import com.android.news.domain.model.Article
import com.android.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke (searchQuery : String,sources : List<String>) : Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}