package com.iris.entrypoint.httpclient

import com.iris.entrypoint.dto.BookDto
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.HttpRequest.PUT
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import io.reactivex.Maybe
import java.net.URI
import javax.inject.Singleton

@Singleton
class CockroachApiLowLevelClient
    (@param:Client("http://localhost:8081") private val httpClient: RxHttpClient){

    fun saveBook(bookDto: BookDto): Maybe<BookDto> {
        val uri: URI = UriBuilder.of("/v1/iupp/book/").build()
        val req: HttpRequest<*> = POST<Any>(uri, bookDto).body(bookDto)
        val flowable = httpClient.retrieve(req, Argument.of(BookDto::class.java))
        return flowable.firstElement()
    }

    fun updateBook(id: Long, bookDto: BookDto): Maybe<BookDto> {
        val uri: URI = UriBuilder.of("/v1/iupp/book/$id").build()
        val req: HttpRequest<*> = PUT<Any>(uri, bookDto).body(bookDto)
        val flowable = httpClient.retrieve(req, Argument.of(BookDto::class.java))
        return flowable.firstElement()
    }
}