package com.gmail.davorlukic82.factorynews.networking.interactors

import com.gmail.davorlukic82.factorynews.model.response.ArticlesResponse
import com.gmail.davorlukic82.factorynews.networking.NewsApiService
import retrofit2.Callback

class ArticleInteractorImpl(private val apiService: NewsApiService): ArticleInteractor {

    override fun getArticles(articlesCallback: Callback<ArticlesResponse>) {
        apiService.getArticles().enqueue(articlesCallback)
    }
}