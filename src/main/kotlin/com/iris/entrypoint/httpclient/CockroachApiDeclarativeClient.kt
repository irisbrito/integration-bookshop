package com.iris.entrypoint.httpclient

import com.iris.entrypoint.dto.BookDto
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import io.reactivex.Maybe
import javax.inject.Singleton

@Client("http://localhost:8081")
@Singleton
interface CockroachApiDeclarativeClient {

    @Post("/v1/iupp/book", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    fun saveBook(@Body book: BookDto) : Maybe<BookDto>

    @Get("/v1/iupp/book", produces = [MediaType.APPLICATION_JSON_STREAM])
    fun getAllBooks() : Maybe<List<BookDto>>

    @Put("/v1/iupp/book/{id}", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    fun updateBook(id: Long, @Body book: BookDto) : Maybe<BookDto>

    @Delete("/v1/iupp/book/{id}", produces = [MediaType.APPLICATION_JSON_STREAM])
    fun deleteBook(id: Long)

}