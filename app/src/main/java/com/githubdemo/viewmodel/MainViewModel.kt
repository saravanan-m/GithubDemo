package com.githubdemo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.githubdemo.model.RootElement
import com.githubdemo.repository.GithubRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var owner = ObservableField<String>("")
    var repo = ObservableField<String>("")

    var loadingVisibility = ObservableBoolean(false)
    var dataVisibility = ObservableBoolean(false)
    var pageNumber = 1
    var initLoading = false
    lateinit var repository: GithubRepository

    var hideKeyboard = MutableLiveData<Boolean>()
    fun fetch(initial: Boolean) {
        if(initial) {
            showData(false)
            pageNumber = 1
            initLoading = true
        }else{
            initLoading = false
        }
        repository.initSearch(owner.get()!!, repo.get()!!,pageNumber)
        pageNumber++
        hideKeyboard.value = true
    }

    fun showData(status: Boolean) {
        if (status) {
            loadingVisibility.set(false)
            dataVisibility.set(true)
        } else {
            loadingVisibility.set(true)
            dataVisibility.set(false)
        }
    }

    fun getLiveData(): MutableLiveData<List<RootElement>> {
        return repository.getPullResultLiveData()
    }
}