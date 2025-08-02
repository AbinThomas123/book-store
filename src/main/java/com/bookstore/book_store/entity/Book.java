package com.bookstore.book_store.entity;

import javax.print.DocFlavor;

public record Book(String bookId,String name,String price,String author,String description) {
}
