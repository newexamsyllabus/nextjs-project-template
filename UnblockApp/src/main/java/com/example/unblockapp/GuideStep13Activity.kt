package com.example.unblockapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GuideStep13Activity : AppCompatActivity() {

    private lateinit var adMobHelper: AdMobHelper
    private lateinit var nextButton: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var feedbackText: TextView

    private val correctAnswerId = R.id.option1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_step13)

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

        headerText.text = "Step 13"
        contentText.text = "Discover the power of compound interest and how it grows your wealth over time."
        financeHeadline.text = "Finance Tip: Compound Interest Magic"
        financeInfo.text = "Compound interest is earning interest on your interest - the eighth wonder of the world according to Einstein."
        progressText.text = "Page 13 of 20"
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
            val intent = Intent(this, GuideStep14Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
