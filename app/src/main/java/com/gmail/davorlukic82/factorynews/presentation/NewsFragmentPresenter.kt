package com.gmail.davorlukic82.factorynews.presentation

import android.content.Context
import android.net.ConnectivityManager
import com.gmail.davorlukic82.factorynews.App
import com.gmail.davorlukic82.factorynews.common.RESPONSE_OK
import com.gmail.davorlukic82.factorynews.model.Article
import com.gmail.davorlukic82.factorynews.model.response.ArticlesResponse
import com.gmail.davorlukic82.factorynews.networking.interactors.ArticleInteractor
import com.gmail.davorlukic82.factorynews.persistance.ArticlesRepository
import com.gmail.davorlukic82.factorynews.ui.fragments.NewsFragmentContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class NewsFragmentPresenter(private val interactor: ArticleInteractor) : NewsFragmentContract.Presenter {

    private lateinit var view: NewsFragmentContract.View
    private val repository = ArticlesRepository()
    private lateinit var news: ArrayList<Article>

    override fun setView(view: NewsFragmentContract.View) {
        this.view = view
    }

    override fun onRequestNews() {
        news = repository.getAllArticles() as ArrayList
        checkList(news)
    }

    private fun checkList(dbData: ArrayList<Article>) {
        var diffInMinutes:Long = 0
        if (dbData.isNotEmpty()) {
            val timeNow = Calendar.getInstance().timeInMillis
            val diff = timeNow - dbData[0].receivedTime!!
            diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff)
        }

        if (dbData.isEmpty() || diffInMinutes >= 5) {
            if (isNetworkConnected()){
                interactor.getArticles(getArticleCallback())
            } else {
                view.onNotConnected()
            }
        } else {
            view.onNewsReceived(dbData)
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun getArticleCallback(): Callback<ArticlesResponse> = object :
        Callback<ArticlesResponse> {
        override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
            view.onCallFailure()
        }

        override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {


            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse(response)
                    else -> view.onSomethingWentWrong(response)
                }
            }else{
                view.onSomethingWentWrong(response)
            }
        }

    }

    private fun handleOkResponse(response: Response<ArticlesResponse>) {
        response.body()?.articles?.run {
            repository.deleteAllArticles()
            this.forEach { repository.saveArticle(it) }
            news = repository.getAllArticles() as ArrayList<Article>
            view.onNewsReceived(news)
        }
    }
}