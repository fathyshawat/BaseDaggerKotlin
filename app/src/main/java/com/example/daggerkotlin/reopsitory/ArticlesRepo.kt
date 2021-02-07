package com.example.daggerkotlin.reopsitory

import androidx.lifecycle.LiveData
import com.example.daggerkotlin.cache.NetworkBoundResource
import com.example.daggerkotlin.cache.Resource
import com.example.daggerkotlin.db.ProjectDao
import com.example.daggerkotlin.model.articles.ArticleModel
import com.example.daggerkotlin.model.articles.ArticlesItem
import com.example.daggerkotlin.remote.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticlesRepo @Inject constructor(
    private val api: ApiService,
    private val dao: ProjectDao
) {



    fun getArticles(source: String?, token: String):
            LiveData<Resource<List<ArticlesItem>?>?>? {
        return object : NetworkBoundResource<List<ArticlesItem>?, ArticleModel?>() {
            override fun saveCallResult(item: ArticleModel?) {
                if (null != item) {
                    dao.saveArticles(item.getArticles())
                }
            }

            override fun loadFromDb(): LiveData<List<ArticlesItem>?> {
                run {
                    return dao.getArticles()
                }
            }

            override fun createCall(): Observable<ArticleModel?> {
                return api.getArticles(source, token)
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribeOn(Schedulers.io())!!
            }
        }.asLiveData
    }

}