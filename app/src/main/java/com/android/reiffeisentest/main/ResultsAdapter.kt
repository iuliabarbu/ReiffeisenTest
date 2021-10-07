package com.android.reiffeisentest.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.reiffeisentest.databinding.ListItemBinding
import com.android.reiffeisentest.api.ResultItem
import javax.inject.Inject

class ResultsAdapter @Inject constructor() :
    ListAdapter<ResultItem, ResultsAdapter.ResultsViewHolder>(DiffCallback) {

    class ResultsViewHolder(
        private var binding: ListItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(p: ResultItem) {
            binding.resultItem = p
            binding.personImage.setClipToOutline(true)
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ResultItem>() {
        override fun areItemsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem.id.value == newItem.id.value
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultsViewHolder {
        return ResultsViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        val p = getItem(position)
        holder.bind(p)
    }

}