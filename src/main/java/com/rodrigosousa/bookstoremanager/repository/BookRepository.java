package com.rodrigosousa.bookstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rodrigosousa.bookstoremanager.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
