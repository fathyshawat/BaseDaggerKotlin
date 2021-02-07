package com.example.daggerkotlin.di.module

import com.example.daggerkotlin.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {


    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}