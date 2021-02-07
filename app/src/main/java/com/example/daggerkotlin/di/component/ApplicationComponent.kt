package com.example.daggerkotlin.di.component

import android.app.Application
import com.example.daggerkotlin.BaseApplication
import com.example.daggerkotlin.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(
    modules = [ NetWorkModule::class, AndroidSupportInjectionModule::class,
        ActivityBindingModule::class, ViewModelFactoryModule::class]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {


    fun inject(application: BaseApplication)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder?

        fun build(): ApplicationComponent?
    }
}