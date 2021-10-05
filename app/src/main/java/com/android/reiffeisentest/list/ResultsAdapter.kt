package com.android.reiffeisentest.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.reiffeisentest.databinding.ListItemBinding
import com.android.reiffeisentest.network.PersonInfo

class ResultsAdapter : ListAdapter<PersonInfo, ResultsAdapter.ResultsViewHolder>(DiffCallback) {

    class ResultsViewHolder(
        private var binding: ListItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(p: PersonInfo) {
            binding.personInfo = p

            binding.personName.setClipToOutline(true)

            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PersonInfo>() {
        override fun areItemsTheSame(oldItem: PersonInfo, newItem: PersonInfo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PersonInfo, newItem: PersonInfo): Boolean {
            return oldItem.id == newItem.id
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