package com.springboot.LMS.service;

import com.springboot.LMS.entity.Books;
import com.springboot.LMS.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;


    @Override
    public Books createOrUpdate(Books books) {
        Books createdBooks = bookRepo.save(books);
        return createdBooks;
    }

    @Override
    public Books findBooksById(Long id) {
        Books book = bookRepo.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        return book;
    }

    @Override
    public List<Books> findByName(String name) {
        List<Books> books = bookRepo.findByBookNameContaining(name);
        return books;
    }

    @Override
    public List<Books> findAllBooks() {
        List<Books> books = bookRepo.findAll();
        return books;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepo.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        bookRepo.deleteById(id);
    }
}
