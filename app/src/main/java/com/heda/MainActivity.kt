package com.heda

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private var speechCallback: (String) -> Unit = {_ -> null}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main)
    }

    fun recognizeSpeech(callback: (String) -> Unit) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }
        resultLauncher.launch(intent)
        speechCallback = callback
    }

    private var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val textSpoken = result.data?.getStringArrayListExtra(
                RecognizerIntent.EXTRA_RESULTS
            )?.get(0)
            speechCallback(textSpoken?: "")
        }
    }
}