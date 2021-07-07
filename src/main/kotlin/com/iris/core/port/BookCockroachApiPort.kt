package com.iris.core.port

import com.iris.core.model.Book
import com.iris.entrypoint.dto.BookDto
import io.reactivex.Maybe
import javax.inject.Singleton

@Singleton
interface BookCockroachApiPort {
    fun createBook(book: Book): Maybe<BookDto>
    fun findAllBooks(): Maybe<List<BookDto>>
    fun updateBook(id: Long, book: Book): Maybe<BookDto>
    fun deleteBook(id: Long)
}