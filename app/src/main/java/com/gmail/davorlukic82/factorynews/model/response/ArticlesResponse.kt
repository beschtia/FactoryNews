package com.gmail.davorlukic82.factorynews.model.response

import com.gmail.davorlukic82.factorynews.model.Article
import com.google.gson.annotations.SerializedName


data class ArticlesResponse (@SerializedName("articles") val articles: ArrayList<Article>)