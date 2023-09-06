package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
