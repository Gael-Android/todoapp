package com.sscc.todoapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomLazyColumn(
    users: List<User>,
) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(users) { user ->
            SimpleCard(title = user.name, subtitle = user.id.toString())
        }
    }
}

@Composable
fun SimpleCard(title: String, subtitle: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp)) // Add a little spacing
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(name = "Test", showSystemUi = true)
@Composable
private fun PreviewTest() {
    val a = listOf(
        User(1, "Alice"),
        User(2, "Bob"),
        User(3, "Charlie"),
        User(4, "David"),
        User(5, "Eve"),
    )

    val b = mutableListOf<User>()

    for (i in 0..100) {
        b.add(User(i, "User $i"))
    }

    CustomLazyColumn(users = b)
}