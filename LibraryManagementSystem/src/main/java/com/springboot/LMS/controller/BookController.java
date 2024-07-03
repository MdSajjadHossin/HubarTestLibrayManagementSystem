package com.springboot.LMS.controller;

import com.springboot.LMS.entity.Authors;
import com.springboot.LMS.entity.Books;
import com.springboot.LMS.service.AuthorServiceImpl;
import com.springboot.LMS.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private AuthorServiceImpl authorService;


    @PostMapping("/")
    public ResponseEntity<Books>createBook(@RequestBody Books books){
        List<Authors> authors = new ArrayList<>();
        for(Authors author : books.getAuthors()){
            Authors foundAuthor = authorService.findAuthorsById(author.getId());
            if(foundAuthor == null){
                return ResponseEntity.notFound().build();
            }
            authors.add(foundAuthor);
        }
        books.setAuthors(authors);
        Books createBooks = bookService.createOrUpdate(books);
        return ResponseEntity.ok(createBooks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Books>updateBooks(@PathVariable Long id, @RequestBody Books books){
        Books isBookExist = bookService.findBooksById(id);
        if(isBookExist == null){
            return ResponseEntity.notFound().build();
        }
        List<Authors> authors = new ArrayList<>();
        for(Authors author : books.getAuthors()){
            Authors foundAuthor = authorService.findAuthorsById(author.getId());
            if(foundAuthor == null){
                return ResponseEntity.notFound().build();
            }
            authors.add(foundAuthor);
        }
        books.setAuthors(authors);
        books.setId(id);
        bookService.createOrUpdate(books);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books>getBookById(@PathVariable Long id){
        Books isBookExist = bookService.findBooksById(id);
        if(isBookExist == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(isBookExist);
    }

    @GetMapping("/search/{name}")
    ResponseEntity<List<Books>>searchBookByName(@PathVariable String name){

        List<Books> booksFound = bookService.findByName(name);
        if(booksFound == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(booksFound);
    }

    @GetMapping("/allBooks")
    public ResponseEntity<List<Books>>getAllBooks(){
        List<Books> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        Books isBookExist = bookService.findBooksById(id);
        if (isBookExist == null){
            return ResponseEntity.notFound().build();
        }
        bookService.deleteBook(id);
        return ResponseEntity.ok("Deletion successful");
    }
}
