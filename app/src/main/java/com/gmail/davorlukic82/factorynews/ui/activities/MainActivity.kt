package com.gmail.davorlukic82.factorynews.ui.activities

import com.gmail.davorlukic82.factorynews.R
import com.gmail.davorlukic82.factorynews.ui.fragments.NewsFragment

class MainActivity :BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        showFragment(NewsFragment.newInstance())
    }
}
