package com.isaacdelosreyes.whathappen.conversation.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ConversationsScreen(
    viewModel: ConversationsViewModel = hiltViewModel(),
    navigateToClickedChat: (String) -> Unit
) {

    val state = viewModel.state

    LazyColumn(content = {
        itemsIndexed(state.conversations) { index, item ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    navigateToClickedChat(item.id)
                }) {
                AsyncImage(
                    model = "https://loremflickr.com/400/400?lock=$index",
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(verticalArrangement = Arrangement.Center) {
                    Text(text = "Chat de prueba", fontWeight = FontWeight.Bold)
                    Text(
                        text = item.messages.last().message,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }, contentPadding = PaddingValues(20.dp), modifier = Modifier.fillMaxSize())
}

fun getDateTime(s: Long): String? {
    return try {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val netDate = Date(s)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}