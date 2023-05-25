package com.nyakshoot.stafftrackersimplenavigation.ui.component

import com.nyakshoot.stafftrackersimplenavigation.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.nyakshoot.stafftrackersimplenavigation.data.models.Employee
import com.nyakshoot.stafftrackersimplenavigation.ui.screens.EmployeeScreen
import com.nyakshoot.stafftrackersimplenavigation.ui.theme.card

@Composable
fun EmployeeCard(employee : Employee, onClickStartEmployeeScreen: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClickStartEmployeeScreen()
            },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(card.hashCode())
        )
    ) {
        Box() {
            Row() {
                Column(
                    modifier = Modifier.padding(5.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_badge_24),
                            contentDescription = "img1",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(0.dp)
                                .size(25.dp)
                        )
                        Text(
                            text = employee.full_name,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.google_sans_bold)),
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_apartment_24),
                            contentDescription = "img2",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(0.dp)
                                .size(25.dp)
                        )
                        Text(
                            text = employee.department,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.google_sans_bold)),
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_work_outline_24),
                            contentDescription = "img3",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(0.dp)
                                .size(25.dp)
                        )
                        Text(
                            text = employee.post,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.google_sans_bold)),
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                        contentDescription = "img4",
                        modifier = Modifier
                            .padding(5.dp)
                            .size(50.dp),
                        alignment = Alignment.CenterEnd
                    )
                }
            }
        }
    }
}