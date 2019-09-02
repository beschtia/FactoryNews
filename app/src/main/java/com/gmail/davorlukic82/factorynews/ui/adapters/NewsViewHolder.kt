package com.gmail.davorlukic82.factorynews.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gmail.davorlukic82.factorynews.common.loadImage
import com.gmail.davorlukic82.factorynews.model.Article
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.view.*


class NewsViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindData(article: Article, onItemSelected: (Int) -> Unit) {

        containerView.setOnClickListener { onItemSelected(adapterPosition) }

        containerView.articleTitle.text = article.title
        containerView.articleImage.loadImage(article.urlToImage)

    }
}