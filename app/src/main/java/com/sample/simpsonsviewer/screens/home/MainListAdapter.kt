package com.sample.simpsonsviewer.screens.home

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.extensions.inflate
import com.sample.simpsonsviewer.models.RelatedTopic
import com.sample.simpsonsviewer.screens.home.MainListAdapter.ListHolder
import com.sample.simpsonsviewer.screens.home.delegates.ListItemClick
import kotlinx.android.synthetic.main.list_item_row.view.*

class MainListAdapter(listener: ListItemClick?) : RecyclerView.Adapter<ListHolder>(), Filterable {
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {

                val charString = charSequence.toString()

                if (charString.isEmpty()) {

                    filteredTopics = originalTopics
                } else {

                    val filteredList = arrayListOf<RelatedTopic>()

                    for (topic in originalTopics) {

                        if (topic.Text.toLowerCase().contains(charString)) {

                            filteredList.add(topic)
                        }
                    }

                    filteredTopics = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = filteredTopics
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                filteredTopics = filterResults.values as List<RelatedTopic>
                notifyDataSetChanged()
            }
        }
    }

    private var itemClick: ListItemClick = listener!!
    private lateinit var originalTopics: List<RelatedTopic>
    private lateinit var filteredTopics: List<RelatedTopic>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val inflatedView = parent.inflate(R.layout.list_item_row, false)
        return ListHolder(inflatedView, itemClick)
    }

    override fun getItemCount(): Int {
        return if (::filteredTopics.isInitialized) filteredTopics.size else 0
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(filteredTopics[position])
    }


    fun updatePostList(relatedTopics: List<RelatedTopic>) {
        this.originalTopics = relatedTopics
        this.filteredTopics = relatedTopics
        notifyDataSetChanged()
        itemClick.updateDetailsFragmentOnStartup(this.filteredTopics[0])
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