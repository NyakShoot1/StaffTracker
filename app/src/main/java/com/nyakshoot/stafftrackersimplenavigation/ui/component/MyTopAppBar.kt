package com.nyakshoot.stafftrackersimplenavigation.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nyakshoot.stafftrackersimplenavigation.R
import com.nyakshoot.stafftrackersimplenavigation.ui.navigation.Screen

@Composable
fun MyTopAppBar(navController: NavController) {
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
        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.Center
                ) {


                    val backStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = backStackEntry?.destination?.route

                    listItems.forEach { item ->
                        if (item.route == currentRoute)
                            Text(
                                text = item.title,
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.google_sans_bold))
                            )
                    }
                }
            }
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
