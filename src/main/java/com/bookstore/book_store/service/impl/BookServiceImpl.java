package com.bookstore.book_store.service.impl;

import com.bookstore.book_store.dto.BookDto;
import com.bookstore.book_store.entity.Book;
import com.bookstore.book_store.exception.BookNotFoundException;
import com.bookstore.book_store.mapper.BookMapper;
import com.bookstore.book_store.repository.BookRepository;
import com.bookstore.book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

   public BookServiceImpl(BookRepository bookRepository){
       this.bookRepository=bookRepository;
   }



    @Override
    public BookDto getBook(String bookId) {

        Book book=bookRepository.findBookByBookId(bookId);
        if(null!=book){
            return BookMapper.toDto(book);
        }else {
            throw new BookNotFoundException("Book with ID " + bookId + " not found");
        }
    }

    @Override
    public List<BookDto> getAllBooks() {

       List<Book> books=bookRepository.findAll();
       List<BookDto> bookDtoList=new ArrayList<>();
       for(Book book:books)
       {
           bookDtoList.add(BookMapper.toDto(book));
       }
        return bookDtoList;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {

       Book book=bookRepository.insert(BookMapper.toEntity(bookDto));
       return BookMapper.toDto(book);
    }

    @Override
    public BookDto updateBookName(BookDto bookDto) {
       bookRepository.updateBookNameByBookId(bookDto.bookId(),bookDto.name());
       Book book =bookRepository.findBookByBookId(bookDto.bookId());

        return BookMapper.toDto(book);
    }

    @Override
    public void deleteBookByBookId(String bookId) {
        bookRepository.deleteBookByBookId(bookId);

    }
}
