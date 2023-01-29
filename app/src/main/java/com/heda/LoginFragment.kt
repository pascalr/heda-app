package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.heda.helpers.G
import com.heda.helpers.fetch
import com.heda.helpers.post
import com.heda.view_models.RouterViewModel
import kotlinx.android.synthetic.main.login_page.*
import okhttp3.FormBody
import okhttp3.RequestBody

class LoginFragment : Fragment(R.layout.login_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            val data = FormBody.Builder()
                .add("username", etUsername.text.toString())
                .add("password", etPassword.text.toString())
                .build()
            post("${G.host}/login/password", data) { response ->
                requireActivity().runOnUiThread(Runnable {
                    findNavController().navigate(R.id.action_loginFragment_to_appFragment3)
                })
            }
        }

        btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}