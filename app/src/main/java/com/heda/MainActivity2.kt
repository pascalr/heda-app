package com.heda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity2 : AppCompatActivity() {

    private fun changePage(page: Int) {
        supportFragmentManager.beginTransaction().apply {
            //replace(R.id.flFragment, homeFragment)
            //commit()
        }
        //pageHistory.add(pageHistory.size, page)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main)

        val default = Fragment(R.layout.login_page)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flMain, default)
            commit()
        }

    }
}