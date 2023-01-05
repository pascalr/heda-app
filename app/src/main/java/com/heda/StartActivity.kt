package com.heda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {

    private val pageHistory: MutableList<Int> = mutableListOf()

    private fun changePage(page: Int) {
        pageHistory.add(pageHistory.size, page)
        setContentView(page)
    }

    override fun onBackPressed() {
        if (pageHistory.size <= 0) {
            super.onBackPressed()
        } else {
            setContentView(pageHistory[pageHistory.size-1])
            pageHistory.removeLast()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changePage(R.layout.login_page)

        val button: Button = findViewById(R.id.btnRegister)
        button.setOnClickListener {
            changePage(R.layout.register_page)
        }

        //Toast.makeText(applicationContext, "StartActivity onCreate", Toast.LENGTH_SHORT).show()

        // How to share data between activities: Try:
        // https://medium.com/android-news/kotlin-series-share-data-between-activities-using-explicit-intent-5a963cceaceb

        // Maybe use: onSaveInstanceState and onRestoreInstanceState like here:
        // https://www.tutorialspoint.com/how-to-save-an-android-activity-state-using-save-instance-state

        //val loggedIn = false
        //if (loggedIn) {
        //    val i = Intent(this@StartActivity, LoginActivity::class.java)
        //    startActivity(i)
        //} else {
        //    val i = Intent(this@StartActivity, SyncingActivity::class.java)
        //    startActivity(i)
        //}
    }
}