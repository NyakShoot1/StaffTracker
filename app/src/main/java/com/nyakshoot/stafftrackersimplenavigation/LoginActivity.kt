package com.nyakshoot.stafftrackersimplenavigation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.nyakshoot.stafftrackersimplenavigation.data.api.ApiHelper
import com.nyakshoot.stafftrackersimplenavigation.data.api.RetrofitBuilder
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.AuthViewModel
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.DocumentViewModel
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.ViewModelFactory
import com.nyakshoot.stafftrackersimplenavigation.ui.screens.LoginScreen
import com.nyakshoot.stafftrackersimplenavigation.ui.theme.StaffTrackerSimpleNavigationTheme

class LoginActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels {
        ViewModelFactory(
            ApiHelper(
                RetrofitBuilder.apiService
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StaffTrackerSimpleNavigationTheme {
                LoginScreen(authViewModel) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        }
    }
}
