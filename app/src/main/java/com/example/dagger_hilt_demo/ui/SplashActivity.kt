package com.example.dagger_hilt_demo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.dagger_hilt_demo.R
import com.example.dagger_hilt_demo.ui.auth.AuthActivity
import com.example.dagger_hilt_demo.ui.base.BaseActivity
import com.example.dagger_hilt_demo.ui.event.BaseEvent
import com.example.dagger_hilt_demo.ui.event.ConstantNavEvent
import com.example.dagger_hilt_demo.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel.navEvent.observe(this, eventObserver)
        lifecycleScope.launch {
            delay(200)
            viewModel.isUserLogin()
        }
    }

    private val eventObserver = Observer<BaseEvent<ConstantNavEvent>> {
        when (val event = it.getEventIfNotHandled()) {
            is ConstantNavEvent.OnResponse -> {
                if (event.data is Boolean) {
                    if (event.data) {
                        moveToHomeScreen()
                    } else {
                        moveToAuthScreen()
                    }
                }
            }
        }
    }

    private fun moveToAuthScreen() {
        startActivity(intentFor<AuthActivity>().clearTask().clearTop())
        this@SplashActivity.finish()
    }

    private fun moveToHomeScreen() {
        startActivity(intentFor<MainActivity>().clearTask().clearTop())
        this@SplashActivity.finish()
    }
}