package com.nyakshoot.stafftrackersimplenavigation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.stafftrackersimplenavigation.R

@Composable
fun NewPhotoScreen(){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp),
            value = text,
            textStyle = TextStyle(fontSize = 28.sp),
            placeholder = {
                Text(
                    text = "Название фото",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 32.sp
                )
            },
            onValueChange = {
                text = it
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                focusedIndicatorColor = Color.Black, // Цвет черты при фокусе
                unfocusedIndicatorColor = Color.Black, // Цвет черты без фокуса
                backgroundColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .border(
                    border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp)
                .clickable {
                    //Открыть внутреннее хранилище и выбрать файл
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Выбрать документ",
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    fontSize = 14.sp
                )
                Icon(
                    modifier = Modifier.size(45.dp),
                    painter = painterResource(id = R.drawable.outline_file_upload_24),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
        Button(
            modifier = Modifier
                .padding(top = 80.dp)
                .height(60.dp)
                .width(130.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                //your onclick code here
            },
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            ),
        ) {
            Text(
                text = "Добавить",
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}