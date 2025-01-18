package com.example.otwsuitmedia.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdScreen(navController: NavController) {
    val users = remember { mutableStateListOf<User>() }
    var page by remember { mutableStateOf(1) }

    // Fetch data saat layar di-render
    LaunchedEffect(Unit) {
        fetchUsers(page) { fetchedUsers ->
            users.addAll(fetchedUsers)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Third Screen",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Second Screen",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center // Konten berada di tengah layar secara vertikal
        ) {
            if (users.isEmpty()) {
                // Tampilkan teks loading jika data belum dimuat
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(0.9f) // Atur lebar konten ke 90% layar
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(users) { user ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                                .clickable {
                                    navController.previousBackStackEntry?.let { entry ->
                                        entry.savedStateHandle["selectedUser"] =
                                            "${user.firstName} ${user.lastName}"
                                        navController.popBackStack()
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically // Foto sejajar dengan teks
                        ) {
                            // Foto profil berbentuk lingkaran di sebelah kiri
                            Image(
                                painter = rememberAsyncImagePainter(user.avatar),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(64.dp) // Ukuran gambar
                                    .clip(CircleShape), // Membuat gambar menjadi lingkaran
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(16.dp)) // Spasi antara foto dan teks

                            // Informasi User (Nama dan Email)
                            Column(
                                modifier = Modifier.fillMaxWidth(), // Teks memenuhi sisa ruang
                                verticalArrangement = Arrangement.Center // Teks sejajar secara vertikal
                            ) {
                                Text(
                                    text = "${user.firstName} ${user.lastName}",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = user.email,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = Color.Gray
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp)) // Spasi antara item

                        // Garis Divider elegan
                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

suspend fun fetchUsers(page: Int, onResult: (List<User>) -> Unit) {
    try {
        // Simulasi fetching data, ganti ini dengan panggilan API nyata
        onResult(
            listOf(
                User("George", "Bluth", "george.bluth@reqres.in", "https://reqres.in/img/faces/1-image.jpg"),
                User("Janet", "Weaver", "janet.weaver@reqres.in", "https://reqres.in/img/faces/2-image.jpg"),
                User("Emma", "Wong", "emma.wong@reqres.in", "https://reqres.in/img/faces/3-image.jpg"),
                User("Eve", "Holt", "eve.holt@reqres.in", "https://reqres.in/img/faces/4-image.jpg"),
                User("Charles", "Morris", "charles.morris@reqres.in", "https://reqres.in/img/faces/5-image.jpg"),
                User("Tracey", "Ramos", "tracey.ramos@reqres.in", "https://reqres.in/img/faces/6-image.jpg"),
                User("Michael", "Lawson", "michael.lawson@reqres.in", "https://reqres.in/img/faces/7-image.jpg"),
                User("Lindsay", "Ferguson", "lindsay.ferguson@reqres.in", "https://reqres.in/img/faces/8-image.jpg"),
                User("Tobias", "Funke", "tobias.funke@reqres.in", "https://reqres.in/img/faces/9-image.jpg"),
                User("Byron", "Fields", "byron.fields@reqres.in", "https://reqres.in/img/faces/10-image.jpg")
            )
        )
    } catch (e: Exception) {
        e.printStackTrace()
        onResult(emptyList())
    }
}