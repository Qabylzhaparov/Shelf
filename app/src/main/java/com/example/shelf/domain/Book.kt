package com.example.shelf.domain

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val status: ShelfStatus
) 

fun List<Book>.filterByStatus(status: ShelfStatus): List<Book> = 
filter { it.status == status}

fun List<Book>.search(query: String): List<Book> {
    if (query.isBlank()) return this
    val q = query.trim().lowercase()
    return filter { book ->
        book.title.lowercase().contains(q) ||
        book.author.lowercase().contains(q)
    }
} 


