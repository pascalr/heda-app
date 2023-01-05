package com.heda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flMain, LoginFragment())
            commit()
        }

    }
}