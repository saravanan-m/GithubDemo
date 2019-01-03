package com.githubdemo.repository

import android.arch.lifecycle.MutableLiveData
import com.githubdemo.model.RootElement
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class GithubRemoteRepository(var retrofit: Retrofit) : GithubRepository {

    var githubLiveData: MutableLiveData<List<RootElement>> = MutableLiveData()


    override fun getPullResultLiveData(): MutableLiveData<List<RootElement>> {
        return githubLiveData;
    }

    override fun initSearch(owner: String, repo: String,page:Int) {
        Single.create<List<RootElement>> { emitter ->
            val githubService = retrofit.create(GithubService::class.java)
            val reposne = githubService.getPullRequestList(owner, repo,page).execute()
            emitter.onSuccess(reposne.body())
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : SingleObserver<List<RootElement>> {
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