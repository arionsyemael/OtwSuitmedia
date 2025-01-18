package com.example.otwsuitmedia.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController, name: String) {
    // State untuk menyimpan nama user yang dipilih
    var selectedUser by remember { mutableStateOf("Selected User Name") }

    // Mendengarkan data dari ThirdScreen (jika ada)
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val selectedUserFlow = savedStateHandle?.getStateFlow<String>("selectedUser", "")
    val selectedUserState by selectedUserFlow?.collectAsState(initial = "") ?: remember { mutableStateOf("") }

    if (selectedUserState.isNotEmpty()) {
        selectedUser = selectedUserState
    }

    // Struktur utama layar menggunakan Scaffold
    Scaffold(
        topBar = {
            // TopAppBar untuk menampilkan judul dan tombol ArrowBack
            TopAppBar(
                title = {
                    Text(
                        text = "Second Screen",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to First Screen",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        // Konten layar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp), // Padding tambahan di sekitar layar
            verticalArrangement = Arrangement.SpaceBetween, // Menyebar konten secara vertikal
            horizontalAlignment = Alignment.CenterHorizontally // Konten di tengah secara horizontal
        ) {
            // Welcome Section: Welcome dan nama
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp), // Jarak atas-bawah
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    fontSize = 26.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start
                )
            }

            // Middle Section: Menampilkan nama user yang dipilih
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f), // Membuat box menempati ruang sisa
                contentAlignment = Alignment.Center // Konten di tengah secara vertikal dan horizontal
            ) {
                Text(
                    text = selectedUser,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Bottom Section: Tombol "Choose a User"
            Button(
                onClick = { navController.navigate("thirdScreen") }, // Navigasi ke ThirdScreen
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(CircleShape), // Membuat tombol bulat
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0D47A1) // Warna biru gelap
                )
            ) {
                Text("Choose a User", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}