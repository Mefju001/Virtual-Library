package com.mefju.Repository;

import com.mefju.Entity.BookJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookJPA,Integer> {
}
