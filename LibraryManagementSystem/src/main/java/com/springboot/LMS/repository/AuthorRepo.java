package com.springboot.LMS.repository;

import com.springboot.LMS.entity.Authors;
import com.springboot.LMS.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Authors, Long> {

    List<Authors> findByAuthorNameContaining(String name);

}
