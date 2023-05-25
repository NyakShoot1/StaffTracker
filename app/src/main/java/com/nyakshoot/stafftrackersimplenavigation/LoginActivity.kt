package com.nyakshoot.stafftrackersimplenavigation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nyakshoot.stafftrackersimplenavigation.ui.screens.LoginScreen
import com.nyakshoot.stafftrackersimplenavigation.ui.theme.StaffTrackerSimpleNavigationTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StaffTrackerSimpleNavigationTheme {
                LoginScreen {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        }
    }
}
