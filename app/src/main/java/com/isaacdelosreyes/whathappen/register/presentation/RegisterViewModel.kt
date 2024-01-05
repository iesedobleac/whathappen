package com.isaacdelosreyes.whathappen.register.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.core.data.model.user.User
import com.isaacdelosreyes.whathappen.register.domain.usecase.InsertUserToFirestoreUseCase
import com.isaacdelosreyes.whathappen.register.domain.usecase.RegisterUserInFirebaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserInFirebaseUseCase: RegisterUserInFirebaseUseCase,
    private val insertUserToFirestoreUseCase: InsertUserToFirestoreUseCase,

    ) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    fun updateName(name: String) {
        state = state.copy(
            name = name
        )
    }

    fun updateEmail(email: String) {
        state = state.copy(
            email = email
        )
    }

    fun updatePassword(password: String) {
        state = state.copy(
            password = password
        )
    }

    fun registerNewUserToFirebaseAuthentication(navigateToConversations: () -> Unit) {
        viewModelScope.launch {
            when (registerUserInFirebaseUseCase(
                email = state.email,
                password = state.password
            )) {
                is Response.Success -> {
                    val user = User(
                        name = state.name,
                        email = state.email,
                        image = "https://loremflickr.com/400/400?lock=${(1..100).random()}"
                    )
                    insertUserToFirestoreUseCase(user)
                    navigateToConversations()
                }

                is Response.Failure -> TODO()
                else -> TODO()
            }
        }
    }
}
