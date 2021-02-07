package com.example.daggerkotlin.model.articles

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class ArticlesItem {

    @PrimaryKey(autoGenerate = true)
    private var id: Int? = null

    @SerializedName("publishedAt")
    private var publishedAt: String? = null

    @SerializedName("author")
    private var author: String? = null

    @SerializedName("urlToImage")
    private var urlToImage: String? = null

    @SerializedName("description")
    private var description: String? = null

    @SerializedName("title")
    private var title: String? = null

    @SerializedName("url")
    private var url: String? = null


    fun setPublishedAt(publishedAt: String?) {
        this.publishedAt = publishedAt
    }

    fun getPublishedAt(): String? {
        return publishedAt
    }

    fun setAuthor(author: String?) {
        this.author = author
    }

    fun getAuthor(): String? {
        return author
    }

    fun setUrlToImage(urlToImage: String?) {
        this.urlToImage = urlToImage
    }

    fun getUrlToImage(): String? {
        return urlToImage
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getDescription(): String? {
        return description
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getTitle(): String? {
        return title
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun getUrl(): String? {
        return url
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }


}