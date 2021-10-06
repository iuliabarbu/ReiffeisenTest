package com.android.reiffeisentest.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.reiffeisentest.databinding.FragmentListBinding

class ListFragment : Fragment() {
    val TAG = ListFragment::class.java.simpleName as String

    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel

        binding.itemsRecycler.adapter = ResultsAdapter()


        binding.itemsRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lm = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount: Int = lm!!.getChildCount()
                val totalItemCount: Int = lm!!.getItemCount()
                val firstVisibleItemPosition: Int = lm!!.findFirstVisibleItemPosition()

                // Load more if we have reach the end to the recyclerView
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    Log.d(TAG, " visibleItemCount = " + visibleItemCount + "firstVisibleItemPosition  " + firstVisibleItemPosition)
                    viewModel.getPaginatedResults()
                }
            }
        })

        return binding.root
    }


}