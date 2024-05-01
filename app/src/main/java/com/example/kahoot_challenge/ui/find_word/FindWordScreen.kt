package com.example.kahoot_challenge.ui.find_word

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kahoot_challenge.TrieViewModel

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindWordScreen(navController: NavHostController, trieViewModel: TrieViewModel) {

    var textState by remember { mutableStateOf(TextFieldValue("")) }

    val wordsList by derivedStateOf {
        trieViewModel.trie.autocomplete(textState.text)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        TextField(
            value = textState,
            onValueChange = { newValue -> textState = newValue },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            if (wordsList.isEmpty()) {
                item {
                    Text("No results found", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                items(wordsList) { word ->
                    Text(text = word, fontSize = 20.sp, modifier = Modifier.padding(16.dp))
                    Divider()
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text(text = "GO BACK")
        }

    }
}
