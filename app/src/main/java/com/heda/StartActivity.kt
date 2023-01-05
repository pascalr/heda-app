package com.heda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        Toast.makeText(applicationContext, "StartActivity onCreate", Toast.LENGTH_SHORT).show()

        val i = Intent(this@StartActivity, MainActivity::class.java)
        startActivity(i)
    }
}