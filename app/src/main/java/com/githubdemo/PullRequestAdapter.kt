package com.githubdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.githubdemo.model.RootElement

class PullRequestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val LOADING = 0
    val DATA = 1

    var data = ArrayList<RootElement>()

    fun addData(list: List<RootElement>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun showLoading(state: Boolean) {
        if (state) {
            if (!data[data.size - 1].loading!!) {
                data.add(RootElement(loading = true))
            }
        } else {
            if(data.size>0) {
                if (data[data.size - 1].loading!!) {
                    data.removeAt(data.size - 1)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(view: ViewGroup, viewtype: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        if (viewtype == LOADING) {
            viewHolder =
                    LoadingViewHolder(LayoutInflater.from(view.context).inflate(R.layout.item_loading, view, false))
        } else {
            viewHolder = DataViewHolder(LayoutInflater.from(view.context).inflate(R.layout.item_data, view, false))
        }
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].loading!!) LOADING else DATA
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is DataViewHolder) {
            viewHolder.bind(data[position])
        }
    }


    class DataViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var url: TextView = view.findViewById(R.id.url)
        var state : TextView = view.findViewById(R.id.state)
        var user : TextView = view.findViewById(R.id.user)
        fun bind(element: RootElement) {
            title.text = element.title
            url.text = element.url
            state.text = element.state
            user.text = element.user?.login
        }
    }

    class LoadingViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }
}