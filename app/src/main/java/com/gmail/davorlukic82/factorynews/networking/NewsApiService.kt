package com.gmail.davorlukic82.factorynews.networking

import com.gmail.davorlukic82.factorynews.model.response.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {

    @GET ("v1/articles?source=bbc-news&sortBy=top")
    fun getArticles(): Call<ArticlesResponse>
}