package com.example.otwsuitmedia.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String
)

@Composable
fun ThirdScreen(navController: NavController) {
    val users = remember { mutableStateListOf<User>() }
    var page by remember { mutableStateOf(1) }

    LaunchedEffect(Unit) {
        fetchUsers(page) { fetchedUsers ->
            users.addAll(fetchedUsers)
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(users) { user ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "selectedUser",
                            "${user.firstName} ${user.lastName}"
                        )
                        navController.popBackStack()
                    }
            ) {
                Image(
                    painter = rememberAsyncImagePainter(user.avatar),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "${user.firstName} ${user.lastName}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = user.email, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

suspend fun fetchUsers(page: Int, onResult: (List<User>) -> Unit) {
    // Simulate fetching data from API here
    // Example:
    onResult(
        listOf(
            User("John", "Doe", "john.doe@example.com", "https://reqres.in/img/faces/1-image.jpg"),
            User("Jane", "Smith", "jane.smith@example.com", "https://reqres.in/img/faces/2-image.jpg")
        )
    )
}
