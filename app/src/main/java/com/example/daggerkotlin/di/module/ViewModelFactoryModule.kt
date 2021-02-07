package com.example.daggerkotlin.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.daggerkotlin.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}