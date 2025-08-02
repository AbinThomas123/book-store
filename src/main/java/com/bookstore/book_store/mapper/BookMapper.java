package com.bookstore.book_store.mapper;

import com.bookstore.book_store.dto.BookDto;
import com.bookstore.book_store.entity.Book;

public class BookMapper {
    public static BookDto toDto(Book book)
    {
        BookDto bookDto=new BookDto(book.bookId(), book.name(), book.author(),book.price(), book.description() );
        return bookDto;
    }

    public static Book toEntity(BookDto bookDto)
    {
        Book book=new Book(bookDto.bookId(),bookDto.name(), bookDto.author(), bookDto.price(), bookDto.description());
        return book;
    }
}
