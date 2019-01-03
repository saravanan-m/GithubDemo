package com.githubdemo.di

import com.githubdemo.MainApplication
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = arrayOf(AndroidInjectionModule::class,BindingModule::class))
interface MainComponent {
    fun inject(application: MainApplication)
}