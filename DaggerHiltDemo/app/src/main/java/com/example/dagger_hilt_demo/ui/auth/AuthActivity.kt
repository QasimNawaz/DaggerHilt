package com.example.dagger_hilt_demo.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.dagger_hilt_demo.R
import com.example.dagger_hilt_demo.ui.base.BaseActivity
import com.example.dagger_hilt_demo.utils.extensions.replaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        replaceFrag(LoginFragment.newInstance())
    }

    private fun replaceFrag(newFragment: Fragment) {
        replaceFragment(
            newFragment,
            R.id.auth_container,
            addBackStack = false,
            clearBackStack = true
        )
    }
}