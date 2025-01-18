package com.example.otwsuitmedia.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var sentence by remember { mutableStateOf(TextFieldValue("")) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    // Background gradient
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF94B8A9),
                        Color(0xFF405985),
                        Color(0xFF465D87),
                        Color(0xFF5189A4),
                        Color(0xFF465D87)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // User icon
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Name input field
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium), // Rounded corners
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Palindrome input field
            TextField(
                value = sentence,
                onValueChange = { sentence = it },
                label = { Text("Palindrome") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium), // Rounded corners
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Check button
            Button(
                onClick = {
                    val isPalindrome = sentence.text.replace(" ", "")
                        .lowercase() == sentence.text.replace(" ", "").reversed().lowercase()
                    dialogMessage = if (isPalindrome) "isPalindrome" else "not palindrome"
                    showDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(CircleShape), // Rounded button
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0D47A1)
                )
            ) {
                Text("CHECK", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Next button
            Button(
                onClick = { navController.navigate("secondScreen/${name.text}") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(CircleShape), // Rounded button
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0D47A1)
                )
            ) {
                Text("NEXT", fontSize = 16.sp, color = Color.White)
            }
        }

        // Dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                },
                text = { Text(dialogMessage) }
            )
        }
    }
}