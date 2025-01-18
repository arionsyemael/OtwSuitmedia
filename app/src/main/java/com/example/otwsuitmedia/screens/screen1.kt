package com.example.otwsuitmedia.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FirstScreen(navController: NavController) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var sentence by remember { mutableStateOf(TextFieldValue("")) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = sentence,
            onValueChange = { sentence = it },
            label = { Text("Palindrome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val isPalindrome = sentence.text.replace(" ", "")
                    .lowercase() == sentence.text.replace(" ", "").reversed().lowercase()
                dialogMessage = if (isPalindrome) "isPalindrome" else "not palindrome"
                showDialog = true // Tampilkan dialog
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("CHECK")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("secondScreen/${name.text}") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("NEXT")
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
