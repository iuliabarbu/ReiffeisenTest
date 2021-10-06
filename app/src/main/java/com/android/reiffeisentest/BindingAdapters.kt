package com.android.reiffeisentest

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.reiffeisentest.list.ResultsAdapter
import com.android.reiffeisentest.list.ResultsApiStatus
import com.android.reiffeisentest.network.Results
import com.bumptech.glide.Glide

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: Results?) {
    val adapter = recyclerView.adapter as ResultsAdapter
    adapter.submitList(data?.results?.let { ArrayList(it) }) // workaround : submitList not working correctly when the same list is submitted
}

@BindingAdapter("itemsApiStatus")
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

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide
            .with(imgView.context)
            .load(imgUrl)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}