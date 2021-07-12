package com.iris.entrypoint.controller.integration

import com.iris.core.mapper.BookConverter
import com.iris.core.port.BookIntegrationPort
import com.iris.entrypoint.dto.BookDto
import com.iris.entrypoint.dto.WriterDto
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.reactivex.Maybe

@Controller("/v1/iupp/book")
class BookIntegrationController(private val bookIntegrationPort: BookIntegrationPort) {

    @Post
    fun postBook(@Body book: BookDto) : MutableHttpResponse<Maybe<BookDto>> {
       return HttpResponse.created(HttpStatus.CREATED).body(BookConverter.bookToBookDto
           (bookIntegrationPort.createBook(book)))
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllBooks(): MutableHttpResponse<Maybe<List<BookDto>>> {
        return HttpResponse.ok(BookConverter.listBookToListBookDto(
            bookIntegrationPort.findAllBooks()))
    }

    @Put("{id}/")
    fun updateBook(id: Long, @Body book: BookDto) : MutableHttpResponse<Maybe<BookDto>> {
        val bookDto = BookDto(id, book.name, book.price, book.description,
            WriterDto(null, book.writer.nome, book.writer.nationality),
        )
        return HttpResponse.ok(BookConverter.bookToBookDto(bookIntegrationPort.updateBook(id, bookDto)))
    }

    @Delete("/{id}")
    fun deleteBook(@PathVariable id: Long) {
        bookIntegrationPort.deleteBook(id)
    }
}
