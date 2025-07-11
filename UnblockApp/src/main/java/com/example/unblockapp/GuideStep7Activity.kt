package com.example.unblockapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GuideStep7Activity : AppCompatActivity() {

    private lateinit var adMobHelper: AdMobHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_step7)

        adMobHelper = AdMobHelper(this)
        adMobHelper.initialize()

        val headerText: TextView = findViewById(R.id.text_header)
        val contentText: TextView = findViewById(R.id.text_content)
        val quizAnswer: EditText = findViewById(R.id.edit_quiz_answer)
        val nextButton: Button = findViewById(R.id.button_next_step)
        val progressText: TextView = findViewById(R.id.text_progress)

        headerText.text = "Step 7"
        contentText.text = "Understand the basics of stock market investing and how to get started."
        progressText.text = "Page 7 of 20"
        nextButton.isEnabled = false

        quizAnswer.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                nextButton.isEnabled = !s.isNullOrBlank()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        nextButton.setOnClickListener {
            val intent = Intent(this, GuideStep8Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
