package com.example.kahoot_challenge

import Trie
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kahoot_challenge.ui.add_word.AddWordScreen
import com.example.kahoot_challenge.ui.find_word.FindWordScreen
import com.example.kahoot_challenge.ui.theme.Kahoot_challengeTheme
import com.example.kahoot_challenge.ui.home.HomeScreen


class TrieViewModel : ViewModel() {
    val trie = Trie().apply {
        insert("car")
        insert("carpet")
        insert("java")
        insert("javascript")
        insert("internet")
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kahoot_challengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val trieViewModel: TrieViewModel = viewModel()

                    NavHost(navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(navController)
                        }
                        composable("find") {
                            FindWordScreen(navController, trieViewModel)
                        }
                        composable("add") {
                            AddWordScreen(navController, trieViewModel)
                        }
                    }
                }
            }
        }
    }
}