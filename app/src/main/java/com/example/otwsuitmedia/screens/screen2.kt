package com.example.otwsuitmedia.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SecondScreen(navController: NavController, name: String) {
    var selectedUser by remember { mutableStateOf("Selected User Name") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome", style = MaterialTheme.typography.headlineSmall)
        Text(text = name, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = selectedUser, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("thirdScreen") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Choose a User")
        }
    }
}
