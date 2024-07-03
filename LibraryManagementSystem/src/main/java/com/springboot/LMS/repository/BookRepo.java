package com.springboot.LMS.repository;

import com.springboot.LMS.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Books, Long> {

    List<Books> findByBookNameContaining(String title);


}
