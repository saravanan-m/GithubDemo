package com.githubdemo.di

import com.githubdemo.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = arrayOf(CommonModule::class))
abstract class BindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}