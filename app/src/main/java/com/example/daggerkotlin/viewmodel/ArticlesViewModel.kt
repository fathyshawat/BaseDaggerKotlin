package com.example.daggerkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daggerkotlin.cache.Resource
import com.example.daggerkotlin.model.articles.ArticlesItem
import com.example.daggerkotlin.reopsitory.ArticlesRepo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(private val repo: ArticlesRepo) : ViewModel() {

    private val disposables = CompositeDisposable()
    private var articleModel: LiveData<Resource<List<ArticlesItem>?>?>? = null


    fun getArticles(source: String, token: String) {
        articleModel = repo.getArticles(source, token)
    }



    val getArticlesLiveData: LiveData<Resource<List<ArticlesItem>?>?>?
        get() = articleModel

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}