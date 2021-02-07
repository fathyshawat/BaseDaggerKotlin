package com.example.daggerkotlin

import com.example.daggerkotlin.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class BaseApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val component = DaggerApplicationComponent
            .builder()
            .application(this)
            ?.build()
        component?.inject(this)

        return component!!

    }

}
