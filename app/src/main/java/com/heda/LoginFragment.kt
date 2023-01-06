package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.heda.helpers.changePage
import com.heda.helpers.clearBackStack
import kotlinx.android.synthetic.main.login_page.*

class LoginFragment : Fragment(R.layout.login_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            changePage(parentFragmentManager, AppFragment())
            clearBackStack(parentFragmentManager)
        }

        btnRegister.setOnClickListener {
            changePage(parentFragmentManager, R.layout.register_page)
        }
    }
}