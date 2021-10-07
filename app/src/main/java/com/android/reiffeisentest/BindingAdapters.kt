package com.android.reiffeisentest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.reiffeisentest.main.ResultsAdapter
import com.android.reiffeisentest.main.ResultsApiStatus
import com.android.reiffeisentest.api.Results
import com.android.reiffeisentest.shared.FormatUtils.FormatHelper
import com.bumptech.glide.Glide
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


@BindingAdapter("bindDate")
fun bindDate(textView: TextView, date: String) {
    textView.text = FormatHelper.convertTimeForDisplay(date)
    textView.invalidate()
}

@BindingAdapter("bindInfoAge","bindInfoCountry")
fun bindInfo(textView: TextView, age: String, country: String) {
    val countryCode = FormatHelper.convertCountryToCode(textView.resources, country)
    var textToDisplay = String.format(
        Locale.getDefault(),
        textView.resources.getString(R.string.full_info), age, countryCode
    )
    textView.text = textToDisplay
    textView.invalidate()
}
