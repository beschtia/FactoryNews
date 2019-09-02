package com.gmail.davorlukic82.factorynews.persistance


import com.gmail.davorlukic82.factorynews.App
import com.gmail.davorlukic82.factorynews.database.ArticlesDatabase
import com.gmail.davorlukic82.factorynews.model.Article
import java.util.*

class ArticlesRepository: ArticlesRepositoryContract {

    private var db = ArticlesDatabase.getInstance(App.getAppContext())

    private var articlesDao = db.articlesDao()

    override fun saveArticle(article: Article) {
        article.receivedTime = Calendar.getInstance().timeInMillis
        articlesDao.addArticle(article)
    }

    override fun getAllArticles(): List<Article> = articlesDao.getArticles()

    override fun deleteAllArticles() {
        articlesDao.deleteArticles()
    }
}