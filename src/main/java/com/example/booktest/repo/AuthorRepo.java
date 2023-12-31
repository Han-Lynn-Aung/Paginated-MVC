package com.example.booktest.repo;

import com.example.booktest.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Long> {
}
