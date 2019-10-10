package com.example.keakr.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.keakr.R
import com.example.keakr.data.model.Beat
import com.example.keakr.ui.profile.KeakListFragment
import com.example.keakr.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity(), ProfileFragment.OnSeeMoreClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(ProfileFragment(), R.id.ic_back)
    }

    override fun onSeeMoreClicked(beats: List<Beat>) {
        addFragmentToBackStack(KeakListFragment.newInstance(beats), frameId = R.id.ic_back)
    }


    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN) }
    }

    fun AppCompatActivity.addFragmentToBackStack(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment)
            .addToBackStack(fragment.javaClass.name)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN) }
    }
}
