package com.bookstore.book_store.service;

import com.bookstore.book_store.dto.BookDto;
import com.bookstore.book_store.entity.Book;

import java.util.List;

public interface BookService {
    public BookDto getBook(String bookId);

    public List<BookDto> getAllBooks();

    public BookDto createBook(BookDto bookDto);


    public BookDto updateBookName(BookDto bookDto);

    public void deletBookByBookId(String bookId);
}
