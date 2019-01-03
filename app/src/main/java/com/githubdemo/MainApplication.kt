package com.githubdemo

import android.app.Activity
import android.app.Application
import com.githubdemo.di.CommonModule
import com.githubdemo.di.DaggerMainComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class MainApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerMainComponent.builder().commonModule(CommonModule(this)).build().inject(this)
    }
}