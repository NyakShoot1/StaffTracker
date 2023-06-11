package com.nyakshoot.stafftrackersimplenavigation.ui.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nyakshoot.stafftrackersimplenavigation.R
import com.nyakshoot.stafftrackersimplenavigation.ui.navigation.Screen

@Composable
fun BackButton(navController: NavController) {
    val listItems = listOf(
        Screen.Employee,
        Screen.NewDocument,
        Screen.NewPhoto
    )
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    listItems.forEach { item ->
        if (item.route == currentRoute) {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .clickable{
                    navController.popBackStack()
                },
                imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
            )
        }
    }
}