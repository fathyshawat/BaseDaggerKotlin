package com.example.daggerkotlin.remote

import com.example.daggerkotlin.model.articles.ArticleModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {



    @GET(Urls.END_POINT)
    fun getArticles(
        @Query(Urls.SOURCE) source: String?,
        @Query(Urls.API_KEY) api: String?
    ): Observable<ArticleModel?>?


}