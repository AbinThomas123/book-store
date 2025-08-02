package com.bookstore.book_store.exception;


public class BookNotFoundException extends RuntimeException{
    // Constructor that takes a message
    public BookNotFoundException(String message) {
        super(message);
    }
}
