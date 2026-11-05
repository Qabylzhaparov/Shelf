package com.example.shelf.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BookTest {

    private fun sampleBooks(): List<Book> = listOf(
        Book("1", "Abay zholy", "Mukhtar Auezov", ShelfStatus.Read),
        Book("2", "Voina i mir", "Tolstoy Lev", ShelfStatus.Reading),
        Book("3", "1984", "George Orwell", ShelfStatus.Want),
    )

    @Test
    fun copy_changes_only_status() {
        val original = sampleBooks().first()
        val updated = original.copy(status = ShelfStatus.Want)

        assertEquals(original.id, updated.id)
        assertEquals(original.title, updated.title)
        assertEquals(original.author, updated.author)
        assertEquals(original.status, ShelfStatus.Read)
        assertEquals(updated.status, ShelfStatus.Want)
    }

    @Test
    fun filterByStatus_return_only_that_shelf() {
        val result = sampleBooks().filterByStatus(ShelfStatus.Want)

        assertEquals(result.size, 1)
        assertTrue(result.all {it.status == ShelfStatus.Want})
    }    

    @Test
    fun search_finds_by_title() {
        val result = sampleBooks().search("Voina")

        assertEquals(1, result.size)
        assertEquals("2", result.single().id)
        assertEquals("Voina i mir", result.single().title)
    }

    @Test
    fun search_finds_by_author() {
        val result = sampleBooks().search("Mukhtar")

        assertEquals(1, result.size)
        assertEquals("1", result.single().id)
        assertEquals("Mukhtar Auezov", result.single().author)
    }

    private fun label(status: ShelfStatus): String = when (status) {
        ShelfStatus.Want -> "want"
        ShelfStatus.Reading -> "reading"
        ShelfStatus.Read -> "read"
    }

    @Test 
    fun when_on_shelfStatus_covers_all_variants() {
        assertEquals("want", label(ShelfStatus.Want))
        assertEquals("reading", label(ShelfStatus.Reading))
        assertEquals("read", label(ShelfStatus.Read))
    }
}