package com.android.reiffeisentest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.reiffeisentest.list.ResultsAdapter
import com.android.reiffeisentest.list.ResultsApiStatus
import com.android.reiffeisentest.network.Results
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

val dateTimeFormatterIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
val dateTimeFormatterOut = SimpleDateFormat("HH:mm", Locale.getDefault())
@BindingAdapter("bindDate")
fun bindDate(textView: TextView, date: String?) {
    val parsedDate = dateTimeFormatterIn.parse(date)
    val formattedDate: String = dateTimeFormatterOut.format(parsedDate)
    textView.text = formattedDate
    textView.invalidate()
}
