package com.nyakshoot.stafftrackersimplenavigation.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MyBottomNavigation(
    navController: NavController
) {
    val listItems = listOf(
        Screen.NotEmployees,
        Screen.Employees,
        Screen.NewEmployee
    )
    androidx.compose.material.BottomNavigation(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(item.route){
                            inclusive = true
                        }
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        painter = painterResource(id = item.iconId),
                        contentDescription = "Icon"
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Black
            )
        }
    }
}