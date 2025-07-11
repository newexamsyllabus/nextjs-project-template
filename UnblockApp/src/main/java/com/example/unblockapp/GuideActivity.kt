package com.example.unblockapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdView

class GuideActivity : AppCompatActivity() {

    private lateinit var adMobHelper: AdMobHelper
    private lateinit var adViewBanner: AdView

    private var currentPage = 1
    private val totalPages = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        adMobHelper = AdMobHelper(this)
        adMobHelper.initialize()

        adViewBanner = findViewById(R.id.adView_banner)
        adMobHelper.loadBannerAd(adViewBanner)

        val headerText: TextView = findViewById(R.id.text_header)
        val contentText: TextView = findViewById(R.id.text_content)
        val quizAnswer: EditText = findViewById(R.id.edit_quiz_answer)
        val nextButton: Button = findViewById(R.id.button_next_step)
        val progressText: TextView = findViewById(R.id.text_progress)

        updatePage(headerText, contentText, progressText, nextButton)

        quizAnswer.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                nextButton.isEnabled = !s.isNullOrBlank()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        nextButton.setOnClickListener {
            if (currentPage % 5 == 0) {
                // Show rewarded ad after every 5 pages
                adMobHelper.loadRewardedAd("ca-app-pub-3940256099942544/5224354917")
                adMobHelper.showRewardedAd(this, {
                    // User earned reward, proceed to next page
                    goToNextPage(headerText, contentText, progressText, quizAnswer, nextButton)
                }, {
                    // Ad closed without reward
                    Toast.makeText(this, "You must watch the ad to proceed", Toast.LENGTH_SHORT).show()
                })
            } else {
                goToNextPage(headerText, contentText, progressText, quizAnswer, nextButton)
            }
        }
    }

    private val guideSteps = listOf(
        Pair("Step 1", "Guide instruction content for step 1."),
        Pair("Step 2", "Guide instruction content for step 2."),
        Pair("Step 3", "Guide instruction content for step 3."),
        Pair("Step 4", "Guide instruction content for step 4."),
        Pair("Step 5", "Guide instruction content for step 5."),
        Pair("Step 6", "Guide instruction content for step 6."),
        Pair("Step 7", "Guide instruction content for step 7."),
        Pair("Step 8", "Guide instruction content for step 8."),
        Pair("Step 9", "Guide instruction content for step 9."),
        Pair("Step 10", "Guide instruction content for step 10."),
        Pair("Step 11", "Guide instruction content for step 11."),
        Pair("Step 12", "Guide instruction content for step 12."),
        Pair("Step 13", "Guide instruction content for step 13."),
        Pair("Step 14", "Guide instruction content for step 14."),
        Pair("Step 15", "Guide instruction content for step 15."),
        Pair("Step 16", "Guide instruction content for step 16."),
        Pair("Step 17", "Guide instruction content for step 17."),
        Pair("Step 18", "Guide instruction content for step 18."),
        Pair("Step 19", "Guide instruction content for step 19."),
        Pair("Step 20", "Guide instruction content for step 20.")
    )

    private fun updatePage(header: TextView, content: TextView, progress: TextView, nextButton: Button) {
        val (headerText, contentText) = guideSteps[currentPage - 1]
        header.text = headerText
        content.text = contentText
        progress.text = "Page $currentPage of $totalPages"
        nextButton.isEnabled = false
        findViewById<EditText>(R.id.edit_quiz_answer).text.clear()
    }

    private fun goToNextPage(header: TextView, content: TextView, progress: TextView, quizAnswer: EditText, nextButton: Button) {
        if (currentPage < totalPages) {
            currentPage++
            updatePage(header, content, progress, nextButton)
        } else {
            Toast.makeText(this, "You have completed the guide!", Toast.LENGTH_LONG).show()
            // Show interstitial ad and navigate to ShareUnlockActivity
            adMobHelper.loadInterstitialAd("ca-app-pub-3940256099942544/1033173712")
            adMobHelper.showInterstitialAd(this, {
                val intent = android.content.Intent(this, ShareUnlockActivity::class.java)
                startActivity(intent)
                finish()
            })
        }
    }
}
