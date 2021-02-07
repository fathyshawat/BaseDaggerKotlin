package com.example.daggerkotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.daggerkotlin.model.articles.ArticlesItem

@Database(entities = arrayOf(ArticlesItem::class), version = 1)
abstract class ProjectDatabase : RoomDatabase() {


     abstract fun projectDao(): ProjectDao
}