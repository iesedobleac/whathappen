package com.isaacdelosreyes.whathappen.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.register.domain.usecase.LoginWithUserCredentialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithUserCredentialsUseCase: LoginWithUserCredentialsUseCase
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

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


    fun loginWithUserCredentials(
        navigateToConversations: () -> Unit,
        navigateToRegisterScreen: () -> Unit
    ) {
        viewModelScope.launch {
            when (val response = loginWithUserCredentialsUseCase(
                email = state.email,
                password = state.password
            )) {
                is Response.Success -> {
                    if (response.data) {
                        navigateToConversations()
                    }
                }

                is Response.Failure -> {
                    navigateToRegisterScreen()
                }

                else -> {
                    //TODO
                }
            }
        }
    }
}
