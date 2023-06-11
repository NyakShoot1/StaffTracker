package com.nyakshoot.stafftrackersimplenavigation

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nyakshoot.stafftrackersimplenavigation.data.api.ApiHelper
import com.nyakshoot.stafftrackersimplenavigation.data.api.RetrofitBuilder
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.DocumentViewModel
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.EmployeeViewModel
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.PhotoViewModel
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.ViewModelFactory
import com.nyakshoot.stafftrackersimplenavigation.ui.screens.MainScreen
import com.nyakshoot.stafftrackersimplenavigation.ui.theme.StaffTrackerSimpleNavigationTheme


class MainActivity : ComponentActivity() {

    private val employeeViewModel: EmployeeViewModel by viewModels {
        ViewModelFactory(
            ApiHelper(
                RetrofitBuilder.apiService
            )
        )
    }

    private val documentViewModel: DocumentViewModel by viewModels {
        ViewModelFactory(
            ApiHelper(
                RetrofitBuilder.apiService
            )
        )
    }

    private val photoViewModel: PhotoViewModel by viewModels {
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
                // A surface container using the 'background' color from the theme
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                MainScreen(employeeViewModel, documentViewModel, photoViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StaffTrackerSimpleNavigationTheme {
        Greeting("Android")
    }
}