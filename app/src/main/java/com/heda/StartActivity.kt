package com.heda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heda.ui.login.LoginActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.syncing_screen)

        //Toast.makeText(applicationContext, "StartActivity onCreate", Toast.LENGTH_SHORT).show()

        // How to share data between activities: Try:
        // https://medium.com/android-news/kotlin-series-share-data-between-activities-using-explicit-intent-5a963cceaceb

        // Maybe use: onSaveInstanceState and onRestoreInstanceState like here:
        // https://www.tutorialspoint.com/how-to-save-an-android-activity-state-using-save-instance-state

        val loggedIn = false
        if (loggedIn) {
            val i = Intent(this@StartActivity, LoginActivity::class.java)
            startActivity(i)
        } else {
            val i = Intent(this@StartActivity, SyncingActivity::class.java)
            startActivity(i)
        }
    }
}