package com.example.cataliftintern

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CataPage() {
    var search by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showImagePickerDialog by remember { mutableStateOf(false) }
    var isLiked by remember { mutableStateOf(false) }
    var likeCount by remember { mutableStateOf(100) }
    var isComment by remember { mutableStateOf(false) }
    var doComment by remember { mutableStateOf(1) }
    val context = LocalContext.current


    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            bitmap?.let {
                val uri = MediaStore.Images.Media.insertImage(
                    context.contentResolver,
                    it,
                    "profile_${System.currentTimeMillis()}",
                    null
                )?.toUri()
                if (uri != null) {
                    imageUri = uri
                }
            }
        }
    // Permission request launcher
    var cameraPermissionGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        cameraPermissionGranted = isGranted
        if (isGranted) {
            cameraLauncher.launch(null)
        }
    }


    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                imageUri = uri
            }
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            "CATA ",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(2.dp)
                                    .background(Color.White)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                "LIFT",
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                                color = Color.White
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Account",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Chat,
                            contentDescription = "Chat",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF000066)
                )
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF000066))
                    .height(60.dp)
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home", tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Explore,
                        contentDescription = "Search", tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Book,
                        contentDescription = "Profile", tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }

    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            item {
                Row {
                    OutlinedTextField(
                        value = search,
                        onValueChange = { search = it },
                        label = { Text("Search") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        },
                        modifier = Modifier.height(64.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(30.dp)
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color.White, shape = RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = {
                            showImagePickerDialog = true
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.AddBox,
                                contentDescription = "Add",
                                tint = Color.Black,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clickable {
                                    showImagePickerDialog = true
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (imageUri != null) {
                                AsyncImage(
                                    model = imageUri,
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(Color.Gray, shape = CircleShape)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = null,
                                    tint = Color.Gray,
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Column {
                            Text("Akhilesh Yadav", fontWeight = FontWeight.Bold)
                            Text(
                                "Founder at Google • 1d • Edited",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(Icons.Default.PersonAdd, contentDescription = null)
                    }
                }

                if (showImagePickerDialog) {
                    AlertDialog(
                        onDismissRequest = { showImagePickerDialog = false },
                        title = { Text("Select Image Source") },
                        text = {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            showImagePickerDialog = false
                                            cameraLauncher.launch(null)
                                        }
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically // <-- Added comma before this line
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CameraAlt,
                                        contentDescription = "Camera",
                                        tint = Color.Black,
                                        modifier = Modifier.size(28.dp)
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text("Camera", fontWeight = FontWeight.Medium, fontSize = 18.sp)
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            showImagePickerDialog = false
                                            galleryLauncher.launch("image/*")
                                        }
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Image,
                                        contentDescription = "Gallery",
                                        tint = Color.Black,
                                        modifier = Modifier.size(28.dp)
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(
                                        "Gallery",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        },
                        confirmButton = {},
                        dismissButton = {}
                    )
                }

                //Things to do now
                Spacer(modifier = Modifier.height(8.dp))
                Text("The Briggs–Rauscher Reaction: A Mesmerizing Chemical Dance 🌈")
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    "This captivating process uses hydrogen peroxide," +
                            " potassium iodate, malonic acid, manganese sulfate, and starch."
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text("Iodine and iodate ions interact to form compounds that shift the solution’s color, while starch amplifies the blue color before it breaks down and starts again.💡")
                Spacer(modifier = Modifier.height(20.dp))
                Text("It's brilliant display of reaction kinetics and chemical equilibrium!")
                Spacer(modifier = Modifier.height(20.dp))
                Text("Ever wondered what makes this colorful cycle possible?")
                Spacer(modifier = Modifier.height(20.dp))
                Text("-@jonathansmith", color = Color.Blue)
                Spacer(modifier = Modifier.height(20.dp))
                Text("All rights reserved to respective owner . DM for credits")
                Spacer(modifier = Modifier.height(20.dp))
                Text("Follow @Science for more", color = Color.Blue)
                Image(
                    painterResource(id = R.drawable.isd),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = null,
                            tint = Color.Yellow
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // small space between icon and text
                        Text(
                            text = "$likeCount likes",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                    }
                    Text(
                        text = "$doComment Comments",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                }

                Row(modifier=Modifier.fillMaxWidth()
                    .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    IconButton(onClick = {
                        isLiked = !isLiked
                        likeCount += if (isLiked) 1 else -1
                    }) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = null,
                            tint=if(isLiked) Color.Blue else Color.Gray
                        )
                    }

                    IconButton(onClick={}){
                        Icon(imageVector = Icons.Default.AddComment,
                            contentDescription = null)
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Share,
                            contentDescription = null)
                    }
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CataPagePreview() {
    CataPage()
}

