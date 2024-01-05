package com.isaacdelosreyes.whathappen.navigation

sealed class Routes(val route: String) {

    object Login : Routes("login")
    object Register : Routes("register")
    object Chat : Routes("chat")
    object Conversations : Routes("conversations")
}