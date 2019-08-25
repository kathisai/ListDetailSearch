package com.sample.simpsonsviewer.screens.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.models.RelatedTopic
import kotlinx.android.synthetic.main.fragment_detail.*

private const val ARG_PARAM1 = "param1"

class DetailFragment : Fragment() {
    private var param1: RelatedTopic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            Log.d("test", "URL" + param1?.Icon?.URL)
            Glide.with(it)
                .load(param1?.Icon?.URL)
                .placeholder(R.drawable.mtrl_popupmenu_background_dark) //TODO replace with better Icon here
                .into(icon_iv)
        }
        var description = param1?.Text?.split(" - ")
        title_tv.text = description?.get(1) ?: "No Data for this item"
    }


    companion object {
        @JvmStatic
        fun newInstance(relatedTopic: RelatedTopic?) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, relatedTopic)
                }
            }
    }
}
