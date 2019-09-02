package com.gmail.davorlukic82.factorynews.ui.fragments

import android.os.Bundle
import android.view.View
import com.gmail.davorlukic82.factorynews.R
import com.gmail.davorlukic82.factorynews.common.loadImage
import com.gmail.davorlukic82.factorynews.model.Article
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.fragment_article

    companion object {

        fun newInstance(article: Article): ArticleFragment{
            val args = Bundle()
            args.putString("TITLE", article.title)
            args.putString("DESCRIPTION", article.description)
            args.putString("IMAGE", article.urlToImage)

            val fragment = ArticleFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        val args = arguments
        detailsTitle.text = args!!.getString("TITLE","")
        detailsDescription.text = args.getString("DESCRIPTION")
        detailsImage.loadImage(args.getString("IMAGE",""))
    }

}