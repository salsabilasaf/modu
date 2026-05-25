package com.example.pam.modul_9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.pam.modul_9.data.BookRepository
import com.example.pam.modul_9.screen.BookScreen
import com.example.pam.modul_9.screen.BookViewModel
import com.example.pam.modul_9.screen.BookViewModelFactory
import com.example.pam.modul_9.ui.theme.PAMTheme

class MainActivity : ComponentActivity() {
    private lateinit var bookViewModel: BookViewModel
    private lateinit var repository: BookRepository
    private lateinit var factory: BookViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = BookRepository()
        factory = BookViewModelFactory(repository)
        bookViewModel = ViewModelProvider(this, factory)[BookViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            PAMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BookScreen(innerPadding, bookViewModel)
                }
            }
        }
    }
}