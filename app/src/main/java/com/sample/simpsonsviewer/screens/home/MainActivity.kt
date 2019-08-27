package com.sample.simpsonsviewer.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.base.SingleFragmentActivity
import com.sample.simpsonsviewer.models.RelatedTopic
import com.sample.simpsonsviewer.screens.details.DetailFragment
import com.sample.simpsonsviewer.screens.home.delegates.ListItemClick

class MainActivity : SingleFragmentActivity(), ListItemClick {

    override fun updateDetailsFragmentOnStartup(relatedTopic: RelatedTopic) {
        if (resources.getBoolean(R.bool.isTablet)) {
            updateDetailsFragment(DetailFragment.newInstance(relatedTopic))
        }
    }

    override fun onItemSelected(relatedTopic: RelatedTopic) {
        hideKeyboard(activity = this)
        if (resources.getBoolean(R.bool.isTablet)) {
            updateDetailsFragment(DetailFragment.newInstance(relatedTopic))
        } else {
            addFragment(DetailFragment.newInstance(relatedTopic))
        }

    }

    override fun createFragment(): Fragment {
        return MainFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
