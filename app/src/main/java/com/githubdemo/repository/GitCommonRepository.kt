package com.githubdemo.repository

import android.arch.lifecycle.MutableLiveData
import com.githubdemo.model.RootElement
import com.githubdemo.room.CacheDao
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GitCommonRepository(val local: GithubLocalRepository, val remote: GithubRemoteRepository) {

    var githubLiveData: MutableLiveData<List<RootElement>> = MutableLiveData()


    fun init(owner: String, repo: String, page: Int) {
        Single.create<List<RootElement>> { emitter ->
            var data = local.initSearch(owner, repo, page)
            if (data == null) {
                data = remote.initSearch(owner, repo, page)
                if (data != null) {
                    val gson = Gson()
                    local.saveData(owner, repo, page, gson.toJson(data))
                }
            }
            emitter.onSuccess(data)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object :
            SingleObserver<List<RootElement>> {
            override fun onSubscribe(d: Disposable?) {

            }

            override fun onError(e: Throwable?) {
                githubLiveData.value = null
            }

            override fun onSuccess(value: List<RootElement>?) {
                githubLiveData.value = value
            }

        })
    }
}