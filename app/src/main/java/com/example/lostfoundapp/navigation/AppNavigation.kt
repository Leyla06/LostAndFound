package com.example.lostfoundapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lostfoundapp.data.AppDatabase
import com.example.lostfoundapp.ui.screen.AddItemScreen
import com.example.lostfoundapp.ui.screen.HomeScreen
import com.example.lostfoundapp.viewmodel.LostFoundViewModel
import com.example.lostfoundapp.viewmodel.LostFoundViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val factory = LostFoundViewModelFactory(
        database.lostItemDao()
    )
    val viewModel: LostFoundViewModel = viewModel(
        factory = factory
    )
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onAddItem = {
                    navController.navigate("add_item")
                }
            )
        }
        composable("add_item") {
            AddItemScreen(
                viewModel = viewModel,
                onItemAdded = {
                    navController.popBackStack()
                }
            )
        }
    }
}