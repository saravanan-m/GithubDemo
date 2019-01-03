package com.githubdemo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.githubdemo.databinding.ActivityMainBinding
import com.githubdemo.repository.GithubRepository
import com.githubdemo.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject
import android.content.Context.INPUT_METHOD_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repo: GithubRepository

    var isLoading: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.repository = repo
        binding.viewModel = viewModel

        val adapter = PullRequestAdapter()
        val linearLayoutManager = LinearLayoutManager(this, OrientationHelper.VERTICAL, false)
        binding.recycler.layoutManager = linearLayoutManager
        binding.recycler.itemAnimator = DefaultItemAnimator()
        binding.recycler.adapter = adapter

        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = linearLayoutManager.getChildCount()
                val totalItemCount = linearLayoutManager.getItemCount()
                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

                if (!isLoading) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        viewModel.fetch(false)
                        adapter.showLoading(true)
                        isLoading = true
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        viewModel.getLiveData().observe(this, Observer { it ->
            adapter.showLoading(false)
            viewModel.showData(true)

            if (it != null) {
                if (viewModel.initLoading) {
                    adapter.data.clear()
                }
                adapter.addData(it!!)
            } else {
                Toast.makeText(applicationContext, "something went wrong", Toast.LENGTH_LONG).show()
            }
            isLoading = false
        })

        viewModel.hideKeyboard.observe(this, Observer {it->
            if(it!!){
                val view = this.currentFocus
                if (view != null) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        })

    }
}
