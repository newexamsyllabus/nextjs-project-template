package com.example.unblockapp

import android.content.Intent
import android.net.VpnService
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var vpnServiceIntent: Intent
    private val vpnRequestCode = 100

    private lateinit var remoteConfigHelper: FirebaseRemoteConfigHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        remoteConfigHelper = FirebaseRemoteConfigHelper(this)

        val inputNumber: EditText = findViewById(R.id.input_number)
        val letsGoButton: Button = findViewById(R.id.button_lets_go)

        letsGoButton.setOnClickListener {
            val number = inputNumber.text.toString()
            if (number.isBlank()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Encrypt number here (not stored)
            // For now, just proceed

            if (remoteConfigHelper.isVpnEnabled()) {
                startVpn()
            } else {
                // Skip VPN, proceed to guide
                startGuide()
            }
        }
    }

    private fun startVpn() {
        val intent = VpnService.prepare(this)
        if (intent != null) {
            startActivityForResult(intent, vpnRequestCode)
        } else {
            onActivityResult(vpnRequestCode, RESULT_OK, null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == vpnRequestCode && resultCode == RESULT_OK) {
            vpnServiceIntent = Intent(this, SmartproxyVpnService::class.java)
            startForegroundService(vpnServiceIntent)
            Toast.makeText(this, getString(R.string.vpn_connected), Toast.LENGTH_SHORT).show()
            startGuide()
        } else {
            Toast.makeText(this, getString(R.string.vpn_permission_denied), Toast.LENGTH_SHORT).show()
        }
    }

    private fun startGuide() {
        val intent = Intent(this, GuideActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, SmartproxyVpnService::class.java))
    }
}
