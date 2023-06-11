package com.nyakshoot.stafftrackersimplenavigation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nyakshoot.stafftrackersimplenavigation.R
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.EmployeeViewModel
import com.nyakshoot.stafftrackersimplenavigation.ui.navigation.Screen

@Composable
fun MyTopAppBar(navController: NavController, employeeViewModel: EmployeeViewModel) {
    val listItems = listOf(
        Screen.NotEmployees,
        Screen.Employees,
        Screen.NewEmployee,
        Screen.Employee,
        Screen.NewDocument,
        Screen.NewPhoto
    )
    TopAppBar(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(navController)

            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route

            listItems.forEach { item ->
                if (item.route == currentRoute)
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        text = item.title,
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.google_sans_bold))
                    )
            }
            EmployeeDropMenu(navController, employeeViewModel)
        }
    }
}

@Composable
fun TopAppBarActionButton(
    iconId: Int,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(painterResource(id = iconId), contentDescription = description)
    }
}