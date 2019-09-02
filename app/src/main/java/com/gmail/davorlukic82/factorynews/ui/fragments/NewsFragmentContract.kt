package com.gmail.davorlukic82.factorynews.ui.fragments

import com.gmail.davorlukic82.factorynews.model.Article
import com.gmail.davorlukic82.factorynews.model.response.ArticlesResponse
import retrofit2.Response

interface NewsFragmentContract {

    interface View{

        fun onNewsReceived(news: ArrayList<Article>)

        fun onNotConnected()

        fun onSomethingWentWrong(response: Response<ArticlesResponse>)

        fun onCallFailure()
    }

    interface Presenter{

        fun setView(view: View)

        fun onRequestNews()
    }
}