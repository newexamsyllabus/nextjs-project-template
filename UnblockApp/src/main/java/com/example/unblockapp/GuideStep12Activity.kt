package com.example.unblockapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GuideStep12Activity : AppCompatActivity() {

    private lateinit var adMobHelper: AdMobHelper
    private lateinit var nextButton: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var feedbackText: TextView

    private val correctAnswerId = R.id.option2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_step12)

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

        headerText.text = "Step 12"
        contentText.text = "Learn about mutual funds and how they can diversify your portfolio."
        financeHeadline.text = "Finance Tip: Mutual Funds Basics"
        financeInfo.text = "Mutual funds pool money from many investors to buy a diversified portfolio of stocks and bonds."
        progressText.text = "Page 12 of 20"
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
            val intent = Intent(this, GuideStep13Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
