package com.mefju.Repository;

import com.mefju.Entity.Book_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book_entity,Integer> {
}
