package com.decagonhq.clads_client.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.decagonhq.clads_client.presentation.ui.EditProfileAccountFragment
import com.decagonhq.clads_client.presentation.ui.EditProfileSecurityFragment

private const val NUM_TABS = 2

class EditProfileViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return EditProfileAccountFragment()
            1 -> return EditProfileSecurityFragment()
        }
        return EditProfileAccountFragment()
    }
}
