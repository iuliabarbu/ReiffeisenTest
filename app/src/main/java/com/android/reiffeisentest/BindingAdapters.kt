package com.android.reiffeisentest

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.reiffeisentest.list.ResultsAdapter
import com.android.reiffeisentest.list.ResultsApiStatus
import com.android.reiffeisentest.network.Results

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: Results?) {
    val adapter = recyclerView.adapter as ResultsAdapter
    adapter.submitList(data?.results)
}

@BindingAdapter("tripsApiStatus")
fun bindStatus(statusImageView: ImageView, status: ResultsApiStatus?) {
    when (status) {
        ResultsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ResultsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ResultsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}