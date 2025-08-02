package com.bookstore.book_store.controller;

import com.bookstore.book_store.dto.BookDto;
import com.bookstore.book_store.exception.BookNotFoundException;
import com.bookstore.book_store.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book-store")
public class BookController {

    BookService bookService;
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> WelcomeMessage(){
      return  new ResponseEntity<>("Welcome to the Book Store",HttpStatus.OK);
    }
    @GetMapping("/{bookId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getBook(@PathVariable String bookId)
    {
        try {
            BookDto bookDto = bookService.getBook(bookId);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>("Book with ID " + bookId + " not found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            e.printStackTrace(); // or use a logger
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookDto>> getAllBooks()
    {

        List<BookDto> bookDtoList=bookService.getAllBooks();
        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> createBooks(@RequestBody BookDto bookDto)
    {

        BookDto bookDto1=bookService.createBook(bookDto);
        return new ResponseEntity<>(bookDto1,HttpStatus.CREATED);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto)
    {
        BookDto bookDto1=  bookService.updateBookName(bookDto);
        return new ResponseEntity<>(bookDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId)
    {
        bookService.deletBookByBookId(bookId);
        return new ResponseEntity<>("Book deleted Successfully "+ bookId ,HttpStatus.OK);
    }

}
