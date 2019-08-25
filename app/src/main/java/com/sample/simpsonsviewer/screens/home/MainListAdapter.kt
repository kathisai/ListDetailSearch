package com.sample.simpsonsviewer.screens.home

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.extensions.inflate
import com.sample.simpsonsviewer.models.RelatedTopic
import com.sample.simpsonsviewer.screens.home.MainListAdapter.ListHolder
import com.sample.simpsonsviewer.screens.home.delegates.ListItemClick
import kotlinx.android.synthetic.main.list_item_row.view.*

class MainListAdapter(listener: ListItemClick?) : RecyclerView.Adapter<ListHolder>() {
    private var itemClick: ListItemClick = listener!!
    private lateinit var relatedTopics: List<RelatedTopic>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val inflatedView = parent.inflate(R.layout.list_item_row, false)
        return ListHolder(inflatedView, itemClick)
    }

    override fun getItemCount(): Int {
        return if (::relatedTopics.isInitialized) relatedTopics.size else 0
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(relatedTopics[position])
    }


    fun updatePostList(relatedTopics: List<RelatedTopic>) {
        this.relatedTopics = relatedTopics
        notifyDataSetChanged()
        itemClick.updateDetailsFragmentOnStartup(this.relatedTopics[0])
    }

    class ListHolder(
        itemView: View,
        itemClick: ListItemClick
    ) : RecyclerView.ViewHolder(itemView) {
        private var view: View = itemView
        private var itemListener: ListItemClick = itemClick

        fun bind(relatedTopic: RelatedTopic) {
            val title = relatedTopic.Text.split(" - ")
            view.itemName.text = title[0]
            view.itemName.setOnClickListener {
                itemListener.onItemSelected(relatedTopic)
            }

        }

    }
}