package com.example.shelf.domain

sealed class ShelfStatus {
    data object Want : ShelfStatus()
    data object Reading : ShelfStatus()
    data object Read : ShelfStatus()
}
