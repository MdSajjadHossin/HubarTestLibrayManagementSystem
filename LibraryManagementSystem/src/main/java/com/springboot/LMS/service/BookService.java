package com.springboot.LMS.service;

import com.springboot.LMS.entity.Books;

import java.util.List;

public interface BookService {

    public Books createOrUpdate(Books book);
    public Books findBooksById(Long id);
    public List<Books> findByName(String name);
    public List<Books> findAllBooks();
    public void deleteBook(Long id);
}
