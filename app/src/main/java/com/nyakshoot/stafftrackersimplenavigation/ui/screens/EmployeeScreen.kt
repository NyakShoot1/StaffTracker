package com.nyakshoot.stafftrackersimplenavigation.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.stafftrackersimplenavigation.R
import com.nyakshoot.stafftrackersimplenavigation.data.models.Employee
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.DocumentViewModel
import com.nyakshoot.stafftrackersimplenavigation.ui.component.DocumentCard
import com.nyakshoot.stafftrackersimplenavigation.utils.formatDate
import okhttp3.HttpUrl


@Composable
fun EmployeeScreen(employee: Employee?, documentViewModel: DocumentViewModel) {
    val context = LocalContext.current

    val state by documentViewModel.documents.collectAsState()

    val list = remember {
        mutableStateOf(documentViewModel.documents.value)
    }

    LaunchedEffect(Unit) {
        documentViewModel.getEmployeeDocuments(employee!!.employee_id.toString())
        list.value = state
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp)
        ) {
            Row() {
                EmployeeInfoText(
                    iconId = R.drawable.baseline_account_circle_24,
                    info = employee!!.full_name
                )
            }
            Row() {
                EmployeeInfoText(
                    iconId = R.drawable.baseline_cake_24,
                    info = formatDate(employee!!.birthday)
                )
            }
            Row() {
                EmployeeInfoText(
                    iconId = R.drawable.baseline_apartment_24,
                    info = employee!!.department
                )
            }
            Row() {
                EmployeeInfoText(
                    iconId = R.drawable.baseline_work_outline_24,
                    info = employee!!.post
                )
            }
            Row() {
                androidx.compose.material3.Text(
                    text = "Принят(а): " + formatDate(employee!!.hire_date),
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.google_sans_bold)),
                    modifier = Modifier.padding(start = 2.dp),
                    fontSize = 18.sp
                )
            }
            Row() {
                androidx.compose.material3.Text(
                    text = "Статус: Действуйщий сотрудник",
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.google_sans_bold)),
                    modifier = Modifier.padding(start = 2.dp),
                    fontSize = 18.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_folder_24),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(0.dp)
                    .size(48.dp)
                    .clickable {

                    },
                colorFilter = ColorFilter.tint(color = Color.White)
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_image_24),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(0.dp)
                    .size(48.dp)
                    .clickable {

                    }
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.85f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(state) { document ->
                DocumentCard(
                    document_name = document.document_name,
                    document_date = formatDate(document.document_date)
                ) {
                    Log.d("penis_employee_Screen", document.document_url)
                    documentViewModel.loadDocument(document, context)
                }
            }
        }
    }
}


@Composable
fun EmployeeInfoText(iconId: Int, info: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(0.dp)
                .size(26.dp)
        )
        androidx.compose.material3.Text(
            text = info,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.google_sans_bold)),
            modifier = Modifier.padding(start = 2.dp),
            fontSize = 18.sp
        )
    }
}