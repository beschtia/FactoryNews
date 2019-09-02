package com.gmail.davorlukic82.factorynews.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.gmail.davorlukic82.factorynews.model.Article
import com.gmail.davorlukic82.factorynews.ui.fragments.ArticleFragment

class ArticlesPagerAdapter(
    fragmentManager: FragmentManager,
    private val articles: ArrayList<Article>
    ) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment {
        return ArticleFragment.newInstance(articles[position])
    }

    override fun getCount(): Int {
        return articles.size
    }
}