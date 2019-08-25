package com.sample.simpsonsviewer.screens.home;

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.screens.home.delegates.ListItemClick
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel: MainListViewModel

    private var listener: ListItemClick? = null
    private lateinit var postListAdapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    // inflate your view here
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_main, container, false)


    // Wait until your View is guaranteed not null to grab View elements
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postListAdapter = MainListAdapter(listener)
        // find your view elements and do stuff here
        viewModel = ViewModelProviders.of(this).get(MainListViewModel::class.java)

        //Handle the Progress bar
        viewModel.loadingVisibility.observe(this, Observer {
            when (it) {
                View.VISIBLE -> mProgressBar.visibility = View.VISIBLE
                View.GONE -> mProgressBar.visibility = View.GONE
            }

        })
        linearLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.adapter = postListAdapter
        viewModel.resultData.observe(this, Observer {
            postListAdapter.updatePostList(it)
        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ListItemClick) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnDetailsFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    companion object {
        fun newInstance() = MainFragment()
    }
}