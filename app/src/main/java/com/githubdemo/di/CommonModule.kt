package com.githubdemo.di

import android.app.Application
import com.githubdemo.repository.GithubRemoteRepository
import com.githubdemo.repository.GithubRepository
import com.githubdemo.MainApplication
import com.githubdemo.repository.GitCommonRepository
import com.githubdemo.repository.GithubLocalRepository
import com.githubdemo.room.AppDatabase
import com.githubdemo.room.CacheDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CommonModule(val application: MainApplication) {

    @Provides
    fun provideRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com")
            .build()
        return retrofit
    }

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideCacheDao(application: Application): CacheDao {
        return AppDatabase.getInstance(application)!!.executingDao()
    }

    @Provides
    fun provideGithubRemoteRepo(retrofit: Retrofit): GithubRemoteRepository {
        return GithubRemoteRepository(retrofit)
    }

    @Provides
    fun provideGithubLocalRepo(dao: CacheDao): GithubLocalRepository {
        return GithubLocalRepository(dao)
    }

    @Provides
    fun provideGithubRepo(local: GithubLocalRepository, remote: GithubRemoteRepository): GitCommonRepository {
        return GitCommonRepository(local, remote)
    }

}