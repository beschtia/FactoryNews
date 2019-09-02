package com.gmail.davorlukic82.factorynews.persistance

import com.gmail.davorlukic82.factorynews.model.Article


interface ArticlesRepositoryContract {

    fun saveArticle(article: Article)
    fun getAllArticles(): List<Article>
    fun deleteAllArticles()
}