package com.githubdemo.di

import android.app.Application
import com.githubdemo.repository.GithubRemoteRepository
import com.githubdemo.repository.GithubRepository
import com.githubdemo.MainApplication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CommonModule(val application: MainApplication) {

    @Provides
    fun provideRetrofit():Retrofit{
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com")
                .build()
        return retrofit
    }

    @Provides
    fun provideApplication():Application{
        return application
    }

    @Provides
    fun provideGithubRepo(retrofit: Retrofit):GithubRepository{
        return GithubRemoteRepository(retrofit)
    }

}