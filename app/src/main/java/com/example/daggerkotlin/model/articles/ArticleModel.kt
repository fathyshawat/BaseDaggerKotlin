package com.example.daggerkotlin.model.articles

import com.google.gson.annotations.SerializedName


class ArticleModel {

    @SerializedName("sortBy")
    private var sortBy: String? = null

    @SerializedName("source")
    private var source: String? = null

    @SerializedName("articles")
    private var articles: List<ArticlesItem>? = null

    @SerializedName("status")
    private var status: String? = null


    fun getSortBy(): String? {
        return sortBy
    }

    fun getSource(): String? {
        return source
    }

    fun getArticles(): List<ArticlesItem>? {
        return articles
    }

    fun getStatus(): String? {
        return status
    }


}