package com.iris.entrypoint

import com.iris.core.model.Book
import com.iris.core.model.Writer
import com.iris.entrypoint.controller.cockroach.BookCockroachApi
import com.iris.entrypoint.dto.BookDto
import com.iris.entrypoint.dto.WriterDto
import com.iris.entrypoint.httpclient.CockroachApiDeclarativeClient
import com.iris.entrypoint.httpclient.CockroachApiLowLevelClient
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Maybe
import java.math.BigDecimal

@MicronautTest
class BookCockroachApiTest : AnnotationSpec() {

    val cockroachApiLowLevelClient = mockk<CockroachApiLowLevelClient>()
    val cockroachApiDeclarativeClient = mockk<CockroachApiDeclarativeClient>()
    val bookCockroachApi = BookCockroachApi(cockroachApiDeclarativeClient, cockroachApiLowLevelClient)

    lateinit var book: Book
    lateinit var bookDto: BookDto
    lateinit var listOfBookDto: List<BookDto>
    lateinit var maybeListOfBookDto: Maybe<List<BookDto>>
    lateinit var maybeBookDto: Maybe<BookDto>

    @BeforeEach
    fun setUp() {
        bookDto = BookDto(1L, "The seven husbands of Evelyn Hugo", BigDecimal.ONE, "Fictional book about a famous actress who had 7 husbands",
            WriterDto(1L, "Taylor Jenkins Reid", "American")
        )
        book = Book(1L, "Poet X", BigDecimal.ONE, "Story about a black girl narrated in poems",
            Writer(1L, "Elizabeth Acevedo", "Dominican")
        )

        listOfBookDto = listOf(bookDto)
        maybeListOfBookDto = Maybe.just(listOfBookDto)
        maybeBookDto = Maybe.just(bookDto)
    }

    @Test
    fun `should get all books`() {
        every { cockroachApiDeclarativeClient.getAllBooks() } returns maybeListOfBookDto

        val result = bookCockroachApi.findAllBooks()

        result shouldBe maybeListOfBookDto
    }

}