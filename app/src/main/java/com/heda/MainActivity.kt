package com.heda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.heda.view_models.RouterViewModel

class MainActivity : AppCompatActivity() {

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