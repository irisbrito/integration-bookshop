package com.iris.entrypoint.controller.integration

import com.iris.core.mapper.BookConverter
import com.iris.core.port.BookIntegrationPort
import com.iris.entrypoint.dto.BookDto
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.reactivex.Maybe

@Controller("/v1/iupp/book")
class BookIntegrationController(private val bookIntegrationPort: BookIntegrationPort) {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllBooks(): MutableHttpResponse<Maybe<List<BookDto>>> {
        return HttpResponse.ok(BookConverter.listBookToListBookDto(
            bookIntegrationPort.findAllBooks()))
    }

}
