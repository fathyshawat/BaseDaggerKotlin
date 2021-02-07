package com.example.daggerkotlin.di.module

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.daggerkotlin.db.ProjectDao
import com.example.daggerkotlin.db.ProjectDatabase
import com.example.daggerkotlin.remote.ApiService
import com.example.daggerkotlin.remote.Urls.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton



@Module(includes = [ViewModelModule::class])
class NetWorkModule {


    @Singleton
    @Provides
    internal fun okHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClient.readTimeout(60, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
        val retrofit =  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java)

    }

    @Singleton
    @Provides
    fun provideDatabase(mApplication: Application): ProjectDatabase {
        return Room.databaseBuilder(mApplication, ProjectDatabase::class.java, "project-db")
            .allowMainThreadQueries()
            .build()
    }



    @Singleton
    @Provides
    fun providesProductDao(projectDatabase: ProjectDatabase): ProjectDao {
        return projectDatabase.projectDao()
    }



}