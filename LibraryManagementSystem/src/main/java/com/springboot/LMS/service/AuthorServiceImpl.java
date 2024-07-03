package com.springboot.LMS.service;

import com.springboot.LMS.entity.Authors;
import com.springboot.LMS.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepo authorRepo;

    @Override
    public Authors createOrUpdate(Authors authors) {
        Authors author = authorRepo.save(authors);
        return author;
    }

    @Override
    public Authors findAuthorsById(Long id) {
        Authors author = authorRepo.findById(id).orElseThrow(()-> new RuntimeException("Author not found"));
        return author;
    }

    @Override
    public List<Authors> findAuthorByName(String name) {
        List<Authors> authors = authorRepo.findByAuthorNameContaining(name);
        return authors;
    }

    @Override
    public List<Authors> findAllAuthors() {
        List<Authors> authors =  authorRepo.findAll();
        return authors;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepo.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepo.deleteById(id);
    }
}
