package com.iris.core.mapper

import com.iris.core.model.Book
import com.iris.core.model.Writer
import com.iris.entrypoint.dto.BookDto
import com.iris.entrypoint.dto.WriterDto
import io.reactivex.Maybe
import kotlin.streams.toList

class BookConverter {
    companion object{
        fun bookDtoToBook(bookDto: BookDto) =
            Book(bookDto.id,bookDto.name,bookDto.price,bookDto.description,
                WriterConverter.writerDtoToWriter(
                    WriterDto(bookDto.id,bookDto.writer.nome,bookDto.writer.nationality)))

        fun bookDtoToBook(bookDto: Maybe<BookDto>) = bookDto.map {
            bookDtoMaybe -> Book(bookDtoMaybe.id,bookDtoMaybe.name,bookDtoMaybe.price,bookDtoMaybe.description,
            WriterConverter.writerDtoToWriter(
                WriterDto(bookDtoMaybe.id,bookDtoMaybe.writer.nome,bookDtoMaybe.writer.nationality)))
        }

        fun bookToBookDto(book: Book) =
            BookDto(book.id,book.name,book.price,book.description,
                WriterConverter.writerToWriterDto(
                    Writer(book.writer.id,book.writer.nome,book.writer.nationality)))

        fun bookToBookDto(book: Maybe<Book>) =
            book.map { bookMaybe ->
                BookDto(bookMaybe.id,bookMaybe.name,bookMaybe.price,bookMaybe.description,
                WriterConverter.writerToWriterDto(Writer(bookMaybe.id,bookMaybe.writer.nome,bookMaybe.writer.nationality)))
            }

        fun listBookToListBookDto(book: Maybe<List<Book>>) = book.map {bookList ->
            bookList.stream().map { book -> BookDto(book.id,book.name,book.price,book.description,
                WriterConverter.writerToWriterDto(
                    Writer(book.writer.id,book.writer.nome,book.writer.nationality)))}.toList() }

        fun listBookDtoToListBook(bookDto: Maybe<List<BookDto>>) = bookDto.map {bookDtoList ->
            bookDtoList.stream().map { bookDto ->
                Book(bookDto.id, bookDto.name, bookDto.price, bookDto.description,
                WriterConverter.writerDtoToWriter(
                    WriterDto(bookDto.writer.id, bookDto.writer.nome, bookDto.writer.nationality)))}.toList() }
    }
}