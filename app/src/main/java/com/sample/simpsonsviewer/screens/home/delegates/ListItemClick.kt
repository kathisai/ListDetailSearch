package com.sample.simpsonsviewer.screens.home.delegates

import com.sample.simpsonsviewer.models.RelatedTopic

/**
 * Interact between MainActivity and MainFragment views
 */
interface ListItemClick {
    /**
     * To open details fragment when user click select from list
     */
    fun onItemSelected(relatedTopic: RelatedTopic)

    /**
     * To update data to detailFragment first time
     */
    fun updateDetailsFragmentOnStartup(relatedTopic: RelatedTopic)
}