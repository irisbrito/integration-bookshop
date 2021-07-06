package com.iris.core.mapper

import com.iris.core.model.Book
import com.iris.core.model.Writer
import com.iris.entrypoint.dto.BookDto
import com.iris.entrypoint.dto.WriterDto
import kotlin.streams.toList

class BookConverter {
    fun bookDtoToBook(bookDto: BookDto) =
        Book(bookDto.id,bookDto.name,bookDto.price,bookDto.description,
            Writer(bookDto.id,bookDto.writer.nome,bookDto.writer.nationality))

    fun bookToBookDto(book: Book) =
        BookDto(book.id,book.name,book.price,book.description,
            WriterDto(book.writer.id,book.writer.nome,book.writer.nationality))

    fun listBookToListBookDto(book: List<Book>) =
        book.stream().map { book -> BookDto(book.id,book.name,book.price,book.description,
            WriterDto(book.writer.id,book.writer.nome,book.writer.nationality)) }.toList()

    fun listBookDtoToListBook(bookDto: List<BookDto>) =
        bookDto.stream().map { bookDto -> Book(bookDto.id, bookDto.name, bookDto.price, bookDto.description,
        Writer(bookDto.writer.id, bookDto.writer.nome, bookDto.writer.nationality))}.toList()
}