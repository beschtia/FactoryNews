package com.gmail.davorlukic82.factorynews.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.davorlukic82.factorynews.R
import com.gmail.davorlukic82.factorynews.model.Article

class NewsAdapter(
    private val onItemSelected: (Int) -> Unit
    ) : RecyclerView.Adapter<NewsViewHolder>() {

    private val data: ArrayList<Article> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(v)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(data[position], onItemSelected)
    }

    fun setData(data: ArrayList<Article>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}