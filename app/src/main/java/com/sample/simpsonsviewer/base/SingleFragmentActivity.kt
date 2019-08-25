package com.sample.simpsonsviewer.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sample.simpsonsviewer.R

abstract class SingleFragmentActivity : AppCompatActivity() {
    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_main
    val fm = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutResId)


        var fragment = fm.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = createFragment()
            // create and commit a fragment transaction
            fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }

    }

    abstract fun createFragment(): Fragment

    fun addFragment(fragmentInstance: Fragment) {
        fm.beginTransaction()
            .add(R.id.fragment_container, fragmentInstance)
            .addToBackStack(fragmentInstance::class.simpleName)
            .commit()
    }

    fun updateDetailsFragment(fragmentInstance: Fragment) {
        fm.beginTransaction()
            .add(R.id.detailContainer, fragmentInstance)
            .addToBackStack(fragmentInstance::class.simpleName)
            .commit()
    }
}
