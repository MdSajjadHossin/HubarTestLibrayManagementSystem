package com.springboot.LMS.service;

import com.springboot.LMS.entity.Authors;
import com.springboot.LMS.entity.Books;
import com.springboot.LMS.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AuthorService {

    public Authors createOrUpdate(Authors authors);
    public Authors findAuthorsById(Long id);
    public List<Authors> findAuthorByName(String name);
    public List<Authors> findAllAuthors();
    public void deleteAuthor(Long id);

}
