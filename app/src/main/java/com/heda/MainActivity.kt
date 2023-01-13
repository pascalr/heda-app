package com.heda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.heda.view_models.RouterViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val router = ViewModelProvider(this)[RouterViewModel::class.java]

        setContentView(R.layout.main)

        //val nav = findNavController(R.layout.login_page)
        //nav.navigate(R.layout.login_page)

        //https://developer.android.com/guide/navigation/navigation-navigate
        //supportFragmentManager.beginTransaction().apply {
        //    replace(R.id.flMain, router.createCurrentPage())
        //    commit()
        //}
    }
}