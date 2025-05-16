package com.example.cataliftintern

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CataPage() {
    var search by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.Bottom // Align children by their bottom edge
                    ) {
                        Text(
                            "CATA ",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(0.1.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(bottom = 0.5.dp) // Adjust to align text baseline if needed
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(2.dp)
                                    .background(Color.White)
                            )
                            Spacer(modifier = Modifier.height(2.dp)) // small gap between line and text
                            Text(
                                "LIFT",
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                                color = Color.White
                            )
                        }
                    }

                },
                actions = {
                    IconButton(onClick = { /* Account */ }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Account",
                            modifier = Modifier.size(28.dp),
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /* Account */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Account",
                            modifier = Modifier.size(28.dp),
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /* Chat */ }) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = "Chat",
                            modifier = Modifier.size(28.dp),
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF000066)
                )
            )
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Row() {
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    label = {
                        Text("Search")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .width(300.dp)
                        .height(80.dp)
                        .padding(16.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(30.dp)
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            IconButton(
                onClick = {},
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AddBox,
                        contentDescription = "Add",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true) 
@Composable
fun CataPagePreview(){
    CataPage()
}