package com.gmail.davorlukic82.factorynews.ui.activities

import androidx.viewpager.widget.ViewPager
import com.gmail.davorlukic82.factorynews.R
import com.gmail.davorlukic82.factorynews.model.Article
import com.gmail.davorlukic82.factorynews.ui.adapters.ArticlesPagerAdapter

class ArticlesDetailsActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var  pagerAdapter: ArticlesPagerAdapter

    override fun getLayoutResourceId() = R.layout.activity_articles_details

    override fun setUpUi() {

        val articles = intent.getParcelableArrayListExtra<Article>("ARTICLES")
        val startPosition = intent.getIntExtra("POSITION", 0)

        supportActionBar!!.title = articles[startPosition].title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow)

        viewPager = findViewById(R.id.viewPager)
        pagerAdapter = ArticlesPagerAdapter(supportFragmentManager, articles)
        viewPager.adapter = pagerAdapter
        viewPager.currentItem = startPosition

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                supportActionBar!!.title = articles[position].title
            }
        })
    }
}
