package com.example.pam.modul_9.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookService {
    @GET("book")
    suspend fun getBooks(): Response<List<Book>>

    @POST("book")
    suspend fun addBook(@Body book: Book): Response<List<Book>>
}