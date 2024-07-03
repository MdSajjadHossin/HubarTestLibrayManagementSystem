package com.springboot.LMS.controller;

import com.springboot.LMS.entity.Authors;
import com.springboot.LMS.service.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorService;

    @PostMapping("/")
    ResponseEntity<Authors>createAuthor(@RequestBody Authors authors){
        Authors author = authorService.createOrUpdate(authors);
        return ResponseEntity.ok(author);
    }

    @PutMapping("/{id}")
    ResponseEntity<Authors>updateAuthor(@PathVariable Long id, @RequestBody Authors authors){
        Authors isAuthorExist = authorService.findAuthorsById(id);
        if(isAuthorExist == null){
            return ResponseEntity.notFound().build();
        }
        authors.setId(id);
        authorService.createOrUpdate(authors);
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/allAuthors")
    ResponseEntity<List<Authors>>getAllAuthors(){
        List<Authors> authors = authorService.findAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    ResponseEntity<Authors>getAuthorById(@PathVariable Long id){
        Authors isAuthorExist = authorService.findAuthorsById(id);
        if(isAuthorExist == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(isAuthorExist);
    }
    @GetMapping("/search/{name}")
    ResponseEntity<List<Authors>>searchAuthor(@PathVariable String name){
        List<Authors> isAuthorsExist = authorService.findAuthorByName(name);
        if(isAuthorsExist == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(isAuthorsExist);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<String>deleteAuthor(@PathVariable Long id){
        Authors isAuthorExist = authorService.findAuthorsById(id);
        if(isAuthorExist == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Author deleted Successfully");
    }
}
