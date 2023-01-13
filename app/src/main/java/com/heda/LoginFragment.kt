package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.heda.view_models.RouterViewModel
import kotlinx.android.synthetic.main.login_page.*

class LoginFragment : Fragment(R.layout.login_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val router = ViewModelProvider(requireActivity())[RouterViewModel::class.java]

        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_appFragment)
            //router.changePage(parentFragmentManager) { -> AppFragment() }
            //router.clearBackStack(parentFragmentManager)
        }

        btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            //router.changePage(parentFragmentManager) { -> Fragment(R.layout.register_page) }
        }
    }
}