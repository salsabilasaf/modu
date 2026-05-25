package com.example.pam.modul_9.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pam.modul_9.data.BookRepository

class BookViewModelFactory(private val repository: BookRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(BookRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {
            Log.e("ERROR", e.message.toString())
        }
        return super.create(modelClass)
    }
}