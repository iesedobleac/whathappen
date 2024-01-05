package com.isaacdelosreyes.whathappen.register.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.isaacdelosreyes.whathappen.R
import com.isaacdelosreyes.whathappen.core.components.PasswordTextField
import com.isaacdelosreyes.whathappen.core.components.RegisterTextField
import com.isaacdelosreyes.whathappen.ui.theme.WhatsApp

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navigateToConversations: () -> Unit
) {

    val state = viewModel.state

    Image(
        painter = painterResource(id = R.drawable.chat_background),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Ingresa tus datos", fontSize = 28.sp, color = WhatsApp)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Necesitamos estos datos para \n poder crear tu cuenta de usuario",
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        RegisterTextField(
            value = state.name,
            placeHolder = "Nombre"
        ) {
            viewModel.updateName(it)
        }
        Spacer(modifier = Modifier.height(10.dp))
        RegisterTextField(
            value = state.email,
            placeHolder = "Email"
        ) {
            viewModel.updateEmail(it)
        }
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            value = state.password,
            onValueChange = {
                viewModel.updatePassword(it)
            })
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.registerNewUserToFirebaseAuthentication(
                    navigateToConversations
                )
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = WhatsApp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Registrarse")
        }
    }
}