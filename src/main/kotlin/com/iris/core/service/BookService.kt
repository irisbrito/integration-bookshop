package com.iris.core.service

import com.iris.core.mapper.BookConverter
import com.iris.core.model.Book
import com.iris.core.port.BookCockroachApiPort
import com.iris.core.port.BookIntegrationPort
import com.iris.entrypoint.dto.BookDto
import io.reactivex.Maybe
import javax.inject.Singleton

@Singleton
class BookService (private val bookCockroachApiPort: BookCockroachApiPort) : BookIntegrationPort {

    override fun createBook(book: BookDto): Maybe<Book> {
        return BookConverter.bookDtoToBook(bookCockroachApiPort.createBook(BookConverter.bookDtoToBook(book)))
    }

    override fun findAllBooks(): Maybe<List<Book>> {
        return BookConverter.listBookDtoToListBook(bookCockroachApiPort.findAllBooks())
    }

    override fun updateBook(id: Long, book: BookDto) : Maybe<Book> {
        return BookConverter.bookDtoToBook(bookCockroachApiPort.updateBook(id,BookConverter.bookDtoToBook(book)))
    }

    override fun deleteBook(id: Long) {
       bookCockroachApiPort.deleteBook(id)
    }
}