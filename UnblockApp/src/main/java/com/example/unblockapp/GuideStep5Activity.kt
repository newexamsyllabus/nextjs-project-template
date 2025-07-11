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

class GuideStep5Activity : AppCompatActivity() {

    private lateinit var adMobHelper: AdMobHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_step5)

        adMobHelper = AdMobHelper(this)
        adMobHelper.initialize()

        val headerText: TextView = findViewById(R.id.text_header)
        val contentText: TextView = findViewById(R.id.text_content)
        val quizAnswer: EditText = findViewById(R.id.edit_quiz_answer)
        val nextButton: Button = findViewById(R.id.button_next_step)
        val progressText: TextView = findViewById(R.id.text_progress)

        headerText.text = "Step 5"
        contentText.text = "Unlock premium countries and enjoy rotating IPs for ultimate freedom."
        progressText.text = "Page 5 of 20"
        nextButton.isEnabled = false

        quizAnswer.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                nextButton.isEnabled = !s.isNullOrBlank()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        nextButton.setOnClickListener {
            adMobHelper.loadRewardedAd("ca-app-pub-3940256099942544/5224354917")
            adMobHelper.showRewardedAd(this, {
                val intent = Intent(this, GuideStep6Activity::class.java)
                startActivity(intent)
                finish()
            }, {
                Toast.makeText(this, "You must watch the ad to proceed", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
