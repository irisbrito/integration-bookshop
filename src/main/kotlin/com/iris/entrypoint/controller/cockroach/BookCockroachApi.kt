package com.iris.entrypoint.controller.cockroach

import com.iris.core.mapper.BookConverter
import com.iris.core.model.Book
import com.iris.core.port.BookCockroachApiPort
import com.iris.entrypoint.dto.BookDto
import com.iris.entrypoint.httpclient.CockroachApiDeclarativeClient
import com.iris.entrypoint.httpclient.CockroachApiLowLevelClient
import io.micronaut.http.annotation.*
import io.reactivex.Maybe

@Controller("/")
class BookCockroachApi(private val cockroachApiDeclarativeClient: CockroachApiDeclarativeClient,
private val cockroachApiLowLevelClient: CockroachApiLowLevelClient)
    : BookCockroachApiPort {

    //val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Post
    override fun createBook(book: Book): Maybe<BookDto> {
        return cockroachApiLowLevelClient.saveBook(BookConverter.bookToBookDto(book))
    }

    @Get
    override fun findAllBooks(): Maybe<List<BookDto>> {
        return cockroachApiDeclarativeClient.getAllBooks()
    }

    @Put
    override fun updateBook(id: Long, book: Book): Maybe<BookDto> {
        return cockroachApiLowLevelClient.updateBook(id, BookConverter.bookToBookDto(book))
    }

    @Delete
    override fun deleteBook(id: Long) {
       cockroachApiDeclarativeClient.deleteBook(id)
    }
}