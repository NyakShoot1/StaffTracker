package com.nyakshoot.stafftrackersimplenavigation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.stafftrackersimplenavigation.R

@Composable
fun LoginScreen(onClickStartMainActivity: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.hand_shake),
            contentDescription = "Hand_shake",
            modifier = Modifier
                .size(200.dp)
        )
        Text(
            modifier = Modifier.padding(bottom = 20.dp),
            text = "StaffTracker",
            color = Color.White,
            fontSize = 40.sp,
            fontFamily = FontFamily(Font(R.font.google_sans_bold))
        )

        val loginState = remember {
            mutableStateOf(TextFieldValue)
        }

        var text by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            modifier = Modifier.padding(bottom = 10.dp),
            value = text,
            textStyle = TextStyle(fontSize = 32.sp),
            placeholder = {
                Text(
                    text = "Логин",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 32.sp
                )
            },
            onValueChange = {
                text = it
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                focusedIndicatorColor = MaterialTheme.colors.primary, // Цвет черты при фокусе
                unfocusedIndicatorColor = MaterialTheme.colors.primary, // Цвет черты без фокуса
                backgroundColor = Color.Black
            )
        )

        var text2 by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = text2,
            textStyle = TextStyle(fontSize = 32.sp),
            placeholder = {
                Text(
                    text = "Пароль",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 32.sp
                )
            },
            onValueChange = {
                text2 = it
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                focusedIndicatorColor = MaterialTheme.colors.primary, // Цвет черты при фокусе
                unfocusedIndicatorColor = MaterialTheme.colors.primary, // Цвет черты без фокуса
                backgroundColor = Color.Black
            )
        )
        Button(
            modifier = Modifier
                .padding(top = 80.dp)
                .height(63.dp)
                .width(276.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                //your onclick code here
                onClickStartMainActivity()
            },
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            ),
        ) {
            Text(
                text = "ВОЙТИ",
                fontSize = 32.sp,
                color = Color.White
            )
        }
    }
}