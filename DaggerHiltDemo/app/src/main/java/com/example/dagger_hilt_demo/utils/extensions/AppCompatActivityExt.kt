package com.example.dagger_hilt_demo.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    addBackStack: Boolean,
    clearBackStack: Boolean
) {
    supportFragmentManager.transact {
        if (addBackStack)
            addToBackStack(fragment::class.java.name)
        if (clearBackStack) {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                supportFragmentManager.popBackStack()
            }
        }
        replace(frameId, fragment)
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}