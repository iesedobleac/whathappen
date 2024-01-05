package com.isaacdelosreyes.whathappen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.isaacdelosreyes.whathappen.chat.presentation.ChatScreen
import com.isaacdelosreyes.whathappen.conversation.presentation.ConversationsScreen
import com.isaacdelosreyes.whathappen.login.presentation.LoginScreen
import com.isaacdelosreyes.whathappen.register.presentation.RegisterScreen

@Composable
fun NavigationHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route,
    ) {
        composable(Routes.Login.route) {
            LoginScreen(navigateToConversations = {
                navController.navigate(Routes.Conversations.route)
            }, navigateToRegisterScreen = {
                navController.navigate(Routes.Register.route)
            })
        }
        composable(Routes.Register.route) {
            RegisterScreen() {
                navController.navigate(Routes.Conversations.route)
            }
        }
        composable(
            route = "${Routes.Chat.route}/{chatId}",
            arguments = listOf(navArgument("chatId") {
                type = NavType.StringType
            })
        ) {
            ChatScreen() {
                navController.popBackStack()
            }
        }
        composable(Routes.Conversations.route) {
            ConversationsScreen {
                navController.navigate("${Routes.Chat.route}/$it")
            }
        }
    }
}