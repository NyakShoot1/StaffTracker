package com.nyakshoot.stafftrackersimplenavigation.ui.component

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.stafftrackersimplenavigation.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewEmployeeDropMenu() {
    val listItems = arrayOf(
        "Отдел кадров",
        "Конструкторский отдел",
        "Финансы",
        "IT-отдел",
        "Маркетинг",
        "Разработка",
        "Аналитический отдел",
        "Продажи"
    )

    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    // remember the selected item
    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }

    // box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        // text field
        TextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(
                    text = "Отдел",
                    color = Color.LightGray
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                textColor = Color.White
            )
        )

        // menu
        ExposedDropdownMenu(
            modifier = Modifier.background(MaterialTheme.colors.primary),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // this is a column scope
            // all the items are added vertically
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = selectedOption
                    expanded = false
                }) {
                    Text(
                        text = selectedOption,
                        fontFamily = FontFamily(Font(R.font.google_sans_bold)),
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}
