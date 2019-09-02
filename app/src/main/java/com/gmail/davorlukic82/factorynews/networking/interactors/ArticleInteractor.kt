package com.gmail.davorlukic82.factorynews.networking.interactors

import com.gmail.davorlukic82.factorynews.model.response.ArticlesResponse
import retrofit2.Callback


interface ArticleInteractor {
    fun getArticles(articlesCallback: Callback<ArticlesResponse>)
}