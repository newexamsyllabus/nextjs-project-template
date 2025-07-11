package com.example.unblockapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GuideStep20Activity : AppCompatActivity() {

    private lateinit var adMobHelper: AdMobHelper
    private lateinit var nextButton: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var feedbackText: TextView

    private val correctAnswerId = R.id.option2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_step20)

        adMobHelper = AdMobHelper(this)
        adMobHelper.initialize()

        val headerText: TextView = findViewById(R.id.text_header)
        val contentText: TextView = findViewById(R.id.text_content)
        val financeHeadline: TextView = findViewById(R.id.text_finance_headline)
        val financeInfo: TextView = findViewById(R.id.text_finance_info)
        radioGroup = findViewById(R.id.radio_group)
        feedbackText = findViewById(R.id.text_feedback)
        nextButton = findViewById(R.id.button_next_step)
        val progressText: TextView = findViewById(R.id.text_progress)

        headerText.text = "Step 20"
        contentText.text = "Congratulations! You've completed the financial literacy guide. Start your investment journey today!"
        financeHeadline.text = "Finance Tip: Take Action Now"
        financeInfo.text = "Knowledge without action is worthless. Start investing today, even with small amounts."
        progressText.text = "Page 20 of 20"
        nextButton.isEnabled = false
        feedbackText.text = ""

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == correctAnswerId) {
                feedbackText.text = "Correct! Well done."
                nextButton.isEnabled = true
            } else {
                feedbackText.text = "Oops! Try again."
                nextButton.isEnabled = false
            }
        }

        nextButton.setOnClickListener {
            Toast.makeText(this, "You have completed the guide!", Toast.LENGTH_LONG).show()
            // Show interstitial ad and navigate to ShareUnlockActivity
            adMobHelper.loadInterstitialAd("ca-app-pub-3940256099942544/1033173712")
            adMobHelper.showInterstitialAd(this, {
                val intent = Intent(this, ShareUnlockActivity::class.java)
                startActivity(intent)
                finish()
            })
        }
    }
}
