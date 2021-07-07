package com.iris.core.port

import com.iris.core.model.Book
import com.iris.entrypoint.dto.BookDto
import io.reactivex.Maybe
import javax.inject.Singleton

@Singleton
interface BookIntegrationPort {
    fun createBook(book: BookDto): Maybe<Book>
    fun findAllBooks(): Maybe<List<Book>>
    fun updateBook(id: Long, book: BookDto): Maybe<Book>
    fun deleteBook(id: Long)
}