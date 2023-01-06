package com.heda

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.heda.view_models.DataViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class StartActivity : AppCompatActivity() {

    private val pageHistory: MutableList<Int> = mutableListOf()

    private fun changePage(page: Int) {
        pageHistory.add(pageHistory.size, page)
        setContentView(page)
    }

    override fun onBackPressed() {
        if (pageHistory.size <= 1) {
            super.onBackPressed()
        } else {
            setContentView(pageHistory[pageHistory.size-2])
            pageHistory.removeLast()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Thread.sleep(10000)

        //Toast.makeText(applicationContext, "StartActivity onCreate", Toast.LENGTH_SHORT).show()

        // How to share data between activities: Try:
        // https://medium.com/android-news/kotlin-series-share-data-between-activities-using-explicit-intent-5a963cceaceb

        // Maybe use: onSaveInstanceState and onRestoreInstanceState like here:
        // https://www.tutorialspoint.com/how-to-save-an-android-activity-state-using-save-instance-state

        val fetchDataViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        val loggedIn = true

        if (loggedIn) {
            changePage(R.layout.activity_main)

            val homeFragment = RecipesFragment()

            //findViewById(R.id.)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, homeFragment)
                commit()
            }
            //changePage(R.layout.home_fragment)
        //    val i = Intent(this@StartActivity, LoginActivity::class.java)
        //    startActivity(i)
        } else {
            changePage(R.layout.login_page)

            val button: Button = findViewById(R.id.btnRegister)
            button.setOnClickListener {
                changePage(R.layout.register_page)
            }
        //    val i = Intent(this@StartActivity, SyncingActivity::class.java)
        //    startActivity(i)
        }
    }
}