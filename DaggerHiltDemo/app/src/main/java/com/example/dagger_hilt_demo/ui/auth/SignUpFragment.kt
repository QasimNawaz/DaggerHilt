package com.example.dagger_hilt_demo.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.dagger_hilt_demo.R
import com.example.dagger_hilt_demo.callback.ActionCallback
import com.example.dagger_hilt_demo.databinding.FragmentLoginBinding
import com.example.dagger_hilt_demo.databinding.FragmentSignUpBinding
import com.example.dagger_hilt_demo.ui.MainActivity
import com.example.dagger_hilt_demo.ui.base.BaseFragment
import com.example.dagger_hilt_demo.ui.event.BaseEvent
import com.example.dagger_hilt_demo.ui.event.ConstantNavEvent
import com.example.dagger_hilt_demo.utils.CustomProgressDialog
import com.example.dagger_hilt_demo.viewmodel.LoginViewModel
import com.example.dagger_hilt_demo.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SignUpFragment : BaseFragment(), ActionCallback {

    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var binding: FragmentSignUpBinding
    private val progressDialog = CustomProgressDialog()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            binding<FragmentSignUpBinding>(inflater, R.layout.fragment_sign_up, container).apply {
                lifecycleOwner = viewLifecycleOwner
                callback = this@SignUpFragment
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activateHideKeyboardUponTouchingScreen(view)
        viewModel.navEvent.observe(viewLifecycleOwner, eventObserver)
    }

    private val eventObserver = Observer<BaseEvent<ConstantNavEvent>> {
        when (val event = it.getEventIfNotHandled()) {
            is ConstantNavEvent.StartLoading -> {
                progressDialog.show(requireContext(), "Please Wait...")
            }
            is ConstantNavEvent.StopLoading -> {
                progressDialog.dialog.cancel()
            }
            is ConstantNavEvent.OnResponse -> {
                Log.d(TAG, "${event.data}")
                startActivity<MainActivity>()
                requireActivity().finish()
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

    companion object {
        private const val TAG = "SignUp_Frg"

        @JvmStatic
        fun newInstance() = SignUpFragment()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.signUp_press -> {
                viewModel.register(
                    binding.signUpEmail.text.toString(),
                    binding.signUpPass.text.toString()
                )
            }
            R.id.signUp_link -> {
                replaceFragment(
                    LoginFragment.newInstance(),
                    R.id.auth_container,
                    addBackStack = true,
                    clearBackStack = false
                )
            }
        }
    }
}