package com.nyakshoot.stafftrackersimplenavigation.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.stafftrackersimplenavigation.R
import com.nyakshoot.stafftrackersimplenavigation.utils.getFileFromUri
import com.nyakshoot.stafftrackersimplenavigation.utils.getFileName

@Composable
fun NewDocumentScreen() {

    val context = LocalContext.current
    val selectedFile = remember { mutableStateOf<Uri?>(null) }
    val FILE_REQUEST_CODE = 200

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
                    text = "Название документа",
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
                    val intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.type = "*/*"
                    intent.addCategory(Intent.CATEGORY_OPENABLE)
                    val chooserIntent = Intent.createChooser(intent, "Выберите файл")
                    (context as? Activity)?.startActivityForResult(chooserIntent, FILE_REQUEST_CODE)

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
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Выбран документ: " + selectedFile.value?.let{
                getFileName(context, it)
            },
            style = MaterialTheme.typography.h4,
            color = Color.White,
            fontSize = 14.sp
        )
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