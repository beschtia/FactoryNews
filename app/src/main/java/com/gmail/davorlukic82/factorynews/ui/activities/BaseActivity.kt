package com.gmail.davorlukic82.factorynews.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.davorlukic82.factorynews.R
import com.gmail.davorlukic82.factorynews.common.showFragment


abstract class BaseActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResourceId())
        setUpUi()
    }

    protected fun showFragment(fragment: Fragment){
        showFragment(R.id.fragmentContainer, fragment)
    }

    abstract fun getLayoutResourceId(): Int
    abstract fun setUpUi()
}