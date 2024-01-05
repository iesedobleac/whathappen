package com.isaacdelosreyes.whathappen.chat.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.isaacdelosreyes.whathappen.R
import com.isaacdelosreyes.whathappen.conversation.presentation.getDateTime
import com.isaacdelosreyes.whathappen.core.data.model.message.Message
import com.isaacdelosreyes.whathappen.ui.theme.OutgoingChatBubble
import com.isaacdelosreyes.whathappen.ui.theme.WhatsApp
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel(), onBackPressed: () -> Unit) {

    val lazyColumnListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val state = viewModel.state

    Image(
        painter = painterResource(id = R.drawable.chat_background),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

    Column(modifier = Modifier.fillMaxSize()) {

        val user = viewModel.getReceivingUserChat()

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(WhatsApp)
                .padding(end = 10.dp, top = 10.dp, bottom = 10.dp)
        ) {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            AsyncImage(
                model = user?.image,
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = user?.name.orEmpty(), color = Color.White)
        }

        LazyColumn(
            verticalArrangement = Arrangement.Bottom,
            state = lazyColumnListState,
            contentPadding = PaddingValues(horizontal = 10.dp),
            content = {
                coroutineScope.launch {
                    lazyColumnListState.scrollToItem(state.messages.size)
                }
                items(state.messages) { message ->
                    MessageCard(messageItem = message, viewModel = viewModel)
                }
            }, modifier = Modifier
                .weight(1f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 10.dp, bottom = 10.dp, end = 10.dp, top = 10.dp)
        ) {
            TextField(
                maxLines = 1,
                singleLine = true,
                value = state.message,
                onValueChange = { viewModel.onChangeMessageValue(it) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier.weight(1f),
                shape = CircleShape,
                placeholder = {
                    Text(text = "Mensaje")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Mood, contentDescription = "")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.AttachFile, contentDescription = "")
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(
                onClick = {
                    if (state.message.isNotEmpty()) {
                        viewModel.updateMessageList()
                    }
                },
                modifier = Modifier
                    .size(55.dp),
                colors = IconButtonDefaults.iconButtonColors(containerColor = WhatsApp)
            ) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = "content description",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun MessageCard(messageItem: Message, viewModel: ChatViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = if (viewModel.isOwnMessage(messageItem)) {
            Alignment.End
        } else {
            Alignment.Start
        },
    ) {
        Card(
            modifier = Modifier.widthIn(max = 320.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (viewModel.isOwnMessage(messageItem)) {
                    OutgoingChatBubble
                } else {
                    Color.White
                }
            ),
        ) {
            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = messageItem.message,
                    modifier = Modifier.weight(weight = 1f, fill = false)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = getDateTime(messageItem.date).orEmpty(),
                    fontSize = 10.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    modifier = Modifier.align(alignment = Alignment.Bottom)
                )
            }
        }
    }
}