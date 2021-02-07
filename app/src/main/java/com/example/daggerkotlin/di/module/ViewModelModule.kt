package com.example.daggerkotlin.di.module

import androidx.lifecycle.ViewModel
import com.example.daggerkotlin.viewmodel.ArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {



    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    internal abstract fun bindsArticlesViewModel(articlesViewModel: ArticlesViewModel): ViewModel


}