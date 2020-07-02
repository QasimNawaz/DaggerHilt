package com.example.dagger_hilt_demo.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger_hilt_demo.R
import com.example.dagger_hilt_demo.adapter.UserListAdapter
import com.example.dagger_hilt_demo.data.model.DataItem
import com.example.dagger_hilt_demo.data.model.ListData
import com.example.dagger_hilt_demo.databinding.ActivityMainBinding
import com.example.dagger_hilt_demo.ui.auth.LoginFragment
import com.example.dagger_hilt_demo.ui.base.BaseActivity
import com.example.dagger_hilt_demo.ui.event.BaseEvent
import com.example.dagger_hilt_demo.ui.event.ConstantNavEvent
import com.example.dagger_hilt_demo.utils.CustomProgressDialog
import com.example.dagger_hilt_demo.viewmodel.LoginViewModel
import com.example.dagger_hilt_demo.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    companion object {
        private const val TAG = "MainScreen"
    }

    private val viewModel: MainActivityViewModel by viewModels()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private val progressDialog = CustomProgressDialog()
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navEvent.observe(this, eventObserver)
        initAdapter()
        viewModel.getList()
    }

    private fun initAdapter() {
        userListAdapter = UserListAdapter(itemClick)
        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
            adapter = userListAdapter
        }
    }

    private val itemClick = object : UserListAdapter.OnItemClickListener {
        override fun onItemClick(item: DataItem) {
            toast(item.name!!)
        }

    }
    private val eventObserver = Observer<BaseEvent<ConstantNavEvent>> {
        when (val event = it.getEventIfNotHandled()) {
            is ConstantNavEvent.StartLoading -> {
                progressDialog.show(this, "Please Wait...")
            }
            is ConstantNavEvent.StopLoading -> {
                progressDialog.dialog.cancel()
            }
            is ConstantNavEvent.OnResponse -> {
                if (event.data is ListData) {
                    val list = event.data.data
                    userListAdapter.update(list as List<DataItem>)
                }
            }
            is ConstantNavEvent.Error -> {
                Log.d(TAG, "${event.error}")
                toast(event.error.toString())
            }
            is ConstantNavEvent.Exception -> {
                Log.d(TAG, "${event.exception?.message.toString()}")
                toast(event.exception?.message.toString())
            }
        }
    }
}