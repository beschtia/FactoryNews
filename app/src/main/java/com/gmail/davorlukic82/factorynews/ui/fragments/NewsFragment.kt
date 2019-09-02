package com.gmail.davorlukic82.factorynews.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.davorlukic82.factorynews.R
import com.gmail.davorlukic82.factorynews.model.Article
import com.gmail.davorlukic82.factorynews.model.response.ArticlesResponse
import com.gmail.davorlukic82.factorynews.networking.BackendFactory
import com.gmail.davorlukic82.factorynews.presentation.NewsFragmentPresenter
import com.gmail.davorlukic82.factorynews.ui.activities.ArticlesDetailsActivity
import com.gmail.davorlukic82.factorynews.ui.adapters.NewsAdapter
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_news.*
import retrofit2.Response
import kotlin.collections.ArrayList

class NewsFragment : BaseFragment(), NewsFragmentContract.View {
    private val adapter by lazy { NewsAdapter {onItemSelected(it)} }

    private lateinit var news: ArrayList<Article>
    private lateinit var progressDialog: AlertDialog
    private val presenter: NewsFragmentContract.Presenter by lazy {
        NewsFragmentPresenter(BackendFactory.getArticleInteractor())
    }

    override fun getLayoutResourceId() = R.layout.fragment_news

    companion object {

        fun newInstance(): Fragment {
            return NewsFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        getData()
    }

    private fun initUi() {
        presenter.setView(this)
        tasksRecyclerView.layoutManager = LinearLayoutManager(context)
        tasksRecyclerView.adapter = adapter
        progressDialog = SpotsDialog(context)
    }

    private fun getData() {
        requestNews()
    }

    private fun requestNews() {
        progressDialog.show()
        presenter.onRequestNews()
    }

    private fun onItemSelected(position: Int) {
        val intent = Intent(context, ArticlesDetailsActivity::class.java).apply {
            putExtra("POSITION", position)
            putParcelableArrayListExtra("ARTICLES", news)
        }
        startActivity(intent)
    }

    override fun onNewsReceived(news: ArrayList<Article>) {
        progressDialog.dismiss()
        this.news = news
        adapter.setData(this.news)
    }

    override fun onNotConnected() {
        progressDialog.dismiss()
        AlertDialog.Builder(context).setTitle(getString(R.string.alert_notConnected_title))
            .setMessage(getString(R.string.alert_notConnected_message))
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .setIcon(android.R.drawable.ic_dialog_alert).show()
    }

    override fun onSomethingWentWrong(response: Response<ArticlesResponse>) {
        progressDialog.dismiss()
        AlertDialog.Builder(context).setTitle(getString(R.string.alert_title))
            .setMessage(getString(R.string.alert_message) + "\nError Code: " + response.code() + "\nMessage: " + response.message())
            .setPositiveButton(getString(R.string.alert_positiveButton), null)
            .show()
    }

    override fun onCallFailure() {
        progressDialog.dismiss()
        AlertDialog.Builder(context).setTitle(getString(R.string.alert_title))
            .setMessage(getString(R.string.alert_message))
            .setPositiveButton(getString(R.string.alert_positiveButton), null)
            .show()
    }

    override fun onResume(){
        super.onResume()
        presenter.setView(this)
        getData()
    }
}
