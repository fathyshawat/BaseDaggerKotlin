package com.example.daggerkotlin.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.example.daggerkotlin.R
import com.example.daggerkotlin.base.BaseActivity
import com.example.daggerkotlin.cache.Status
import com.example.daggerkotlin.databinding.ActivityMainBinding
import com.example.daggerkotlin.model.articles.ArticlesItem
import com.example.daggerkotlin.remote.Urls
import com.example.daggerkotlin.view.adapter.ArticlesAdapter
import com.example.daggerkotlin.viewmodel.ArticlesViewModel

class MainActivity : BaseActivity<ActivityMainBinding, ArticlesViewModel>() {

    private lateinit var adapter: ArticlesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var data: ArrayList<ArticlesItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        viewModel.getArticles(Urls.NEXT_PAGE, Urls.TOKEN)

        data = ArrayList()
        adapter = ArticlesAdapter(data)
        dataBinding.recycler.adapter = adapter
        dataBinding.recycler.layoutManager = linearLayoutManager

//        val skeletonScreen = Skeleton.bind(dataBinding.recycler)
//            .adapter(adapter)
//            .shimmer(true)
//            .angle(20)
//            .frozen(false)
//            .duration(1200)
//            .count(10)
//            .load(R.layout.item_articles)
//            .show()
        viewModel.getArticlesLiveData?.observe(this@MainActivity, Observer { articles ->
            if (null != articles && (articles.status === Status.ERROR ||
                        articles.status === Status.SUCCESS)
            ) {
                adapter.setArticles(articles.data as ArrayList<ArticlesItem>?)
//                dataBinding.recycler.postDelayed( {
//
//                    skeletonScreen.hide()
//                    Log.d("gjjgjgg00","done")
//                }, 3000)

            }
        })
    }

    override fun layoutRes(): Int =
        R.layout.activity_main


    override fun getViewModel(): Class<ArticlesViewModel> = ArticlesViewModel::class.java

}