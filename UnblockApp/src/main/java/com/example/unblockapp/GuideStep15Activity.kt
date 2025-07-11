package com.example.unblockapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GuideStep15Activity : AppCompatActivity() {

    private lateinit var adMobHelper: AdMobHelper
    private lateinit var nextButton: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var feedbackText: TextView

    private val correctAnswerId = R.id.option2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_step15)

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

        headerText.text = "Step 15"
        contentText.text = "Understand the importance of emergency funds and financial planning."
        financeHeadline.text = "Finance Tip: Emergency Fund Essentials"
        financeInfo.text = "An emergency fund should cover 3-6 months of expenses for unexpected situations."
        progressText.text = "Page 15 of 20"
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
            // Show rewarded ad after every 5 pages (15 is divisible by 5)
            adMobHelper.loadRewardedAd("ca-app-pub-3940256099942544/5224354917")
            adMobHelper.showRewardedAd(this, {
                val intent = Intent(this, GuideStep16Activity::class.java)
                startActivity(intent)
                finish()
            }, {
                Toast.makeText(this, "You must watch the ad to proceed", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
