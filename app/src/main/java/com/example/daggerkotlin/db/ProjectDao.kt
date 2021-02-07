package com.example.daggerkotlin.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.daggerkotlin.model.articles.ArticlesItem

@Dao
interface ProjectDao {

    @Query("SELECT* FROM articlesitem")
    fun getArticles(): LiveData<List<ArticlesItem>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticles(articleEntities: List<ArticlesItem>?)

}