package com.heda

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.heda.view_models.RouterViewModel

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val router = ViewModelProvider(this)[RouterViewModel::class.java]

        setContentView(R.layout.main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flMain, router.createCurrentPage())
            commit()
        }
    }
}