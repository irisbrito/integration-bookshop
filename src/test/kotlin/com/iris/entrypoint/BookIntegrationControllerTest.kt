package com.iris.entrypoint

import com.iris.core.mapper.BookConverter
import com.iris.core.port.BookIntegrationPort
import com.iris.entrypoint.controller.integration.BookIntegrationController
import com.iris.entrypoint.dto.BookDto
import com.iris.entrypoint.dto.WriterDto
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Maybe
import java.math.BigDecimal

@MicronautTest
class BookIntegrationControllerTest : AnnotationSpec() {

    val bookIntegrationPort = mockk<BookIntegrationPort>()
    val bookIntegration = BookIntegrationController(bookIntegrationPort)

    lateinit var bookDto: BookDto
    lateinit var listOfBookDto: List<BookDto>
    lateinit var maybeListOfBookDto: Maybe<List<BookDto>>
    lateinit var maybeBookDto: Maybe<BookDto>

    @BeforeEach
    fun setUp() {
        bookDto = BookDto(1L, "It ends with us", BigDecimal.ONE, "Its a romance about toxic relationship",
            WriterDto(1L, "It ends with us", "American")
        )

        listOfBookDto = listOf(bookDto)
        maybeListOfBookDto = Maybe.just(listOfBookDto)
        maybeBookDto = Maybe.just(bookDto)
    }

    @Test
    fun `should create a book`() {
        every { BookConverter.bookToBookDto(bookIntegrationPort.createBook(any())) } returns maybeBookDto

        val result = bookIntegration.postBook(bookDto)

        result.status shouldBe HttpStatus.CREATED
    }

    @Test
    fun `should get all books`() {
        every { BookConverter.listBookToListBookDto(bookIntegrationPort.findAllBooks()) } returns maybeListOfBookDto

        val result = bookIntegration.getAllBooks()

        result.status() shouldBe HttpStatus.OK
    }

    @Test
    fun `should delete book by id`() {
        every { bookIntegrationPort.deleteBook(any()) } returns Unit

        val result = bookIntegration.deleteBook(bookDto.id!!)

        result shouldBe Unit
    }

}