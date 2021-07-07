package com.iris.entrypoint.httpclient

import com.iris.entrypoint.dto.BookDto
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import io.reactivex.Maybe
import javax.inject.Singleton

@Client("http://localhost:8081")
@Singleton
interface CockroachApiDeclarativeClient {

    @Post("/v1/iupp/book", produces = [MediaType.APPLICATION_JSON_STREAM])
    fun saveBook(book: BookDto) : Maybe<BookDto>

    @Get("/v1/iupp/book", produces = [MediaType.APPLICATION_JSON_STREAM])
    fun getAllBooks() : Maybe<List<BookDto>>

    @Delete("/v1/iupp/book/{id}", produces = [MediaType.APPLICATION_JSON_STREAM])
    fun deleteBook(id: Long)

}