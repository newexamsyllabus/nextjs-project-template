package com.example.unblockapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ShareUnlockActivity : AppCompatActivity() {

    private var shareCount = 0
    private val shareTarget = 10

    private lateinit var shareProgressText: TextView
    private lateinit var shareButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_unlock)

        shareProgressText = findViewById(R.id.text_share_progress)
        shareButton = findViewById(R.id.button_share)

        updateShareProgress()

        shareButton.setOnClickListener {
            shareApp()
        }
    }

    private fun updateShareProgress() {
        shareProgressText.text = "Shared $shareCount out of $shareTarget"
        shareButton.isEnabled = shareCount < shareTarget
        if (shareCount >= shareTarget) {
            Toast.makeText(this, "Premium access unlocked!", Toast.LENGTH_LONG).show()
            // TODO: Unlock premium features or final result here
        }
    }

    private fun shareApp() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out Unblock App!")
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Download Unblock App from the Play Store: https://play.google.com/store/apps/details?id=com.example.unblockapp")
        startActivityForResult(Intent.createChooser(shareIntent, "Share Unblock App"), 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            // Assume share was successful
            shareCount++
            updateShareProgress()
        }
    }
}
