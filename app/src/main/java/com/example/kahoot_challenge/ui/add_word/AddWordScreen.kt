package com.example.kahoot_challenge.ui.add_word

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kahoot_challenge.TrieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordScreen(navController: NavHostController, trieViewModel: TrieViewModel) {

    var textState by remember { mutableStateOf(TextFieldValue("")) }

    var validateText by remember { mutableStateOf("") }

    var validateColor by remember { mutableStateOf(Color.Red) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ) {
        TextField(
            value = textState,
            onValueChange = { newValue -> textState = newValue },
            label = { Text("Enter word") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = validateText, color = validateColor)
        Spacer(modifier = Modifier.height(16.dp))
        Row(

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        {
            Button(
                onClick = { navController.popBackStack() }
            )
            {
                Text(text = "GO BACK")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    if (validateWord(textState.text)) {
                        trieViewModel.trie.insert(textState.text.toLowerCase())
                        validateText = "Word has been successfully added"
                        validateColor = Color.Green
                    } else {
                        validateText = "Enter a word that contains only alphabetical letters"
                        validateColor = Color.Red
                    }
                }
            )
            {
                Text(text = "ADD")
            }
        }

    }
}


fun validateWord(word: String): Boolean {
    val regex = "^[a-zA-Z]+$".toRegex()
    return regex.matches(word)
}