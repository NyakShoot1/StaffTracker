package com.nyakshoot.stafftrackersimplenavigation.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.stafftrackersimplenavigation.R
import com.nyakshoot.stafftrackersimplenavigation.data.models.Admin
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(authViewModel: AuthViewModel, onClickStartMainActivity: () -> Unit) {
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current
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

        var login by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            modifier = Modifier.padding(bottom = 10.dp),
            value = login,
            textStyle = TextStyle(fontSize = 32.sp),
            placeholder = {
                Text(
                    text = "Логин",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 32.sp
                )
            },
            onValueChange = {
                login = it
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                focusedIndicatorColor = MaterialTheme.colors.primary, // Цвет черты при фокусе
                unfocusedIndicatorColor = MaterialTheme.colors.primary, // Цвет черты без фокуса
                backgroundColor = Color.Black
            )
        )

        var password by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = password,
            textStyle = TextStyle(fontSize = 32.sp),
            placeholder = {
                Text(
                    text = "Пароль",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 32.sp
                )
            },
            onValueChange = {
                password = it
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
                authViewModel.login(Admin(login.text, password.text))
                if (authState.message != "")
                    onClickStartMainActivity()
                else if (authState.error != "")
                    Toast.makeText(context, "Ошибка авторизации", Toast.LENGTH_SHORT).show()
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